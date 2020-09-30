package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.customers.entity.Customer;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomersResource {

    @Inject
    CustomerService customerService;

    @GET
    public Response all(){
        return Response.ok(Customer.listAll()).build();
    }

    @Counted
    @POST
    public Response create(Customer customer, @Context UriInfo uriInfo){
        System.out.println(customer);
        UUID customerId = customerService.create(customer);
        return Response
                .created(uriInfo.getRequestUriBuilder().path(CustomersResource.class, "find").build(customerId))
                .build();
    }

    @Traced
    @GET
    @Path("{customerId}")
    public Response find(@PathParam("customerId") UUID customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return Response.ok(customer).build();
//        if (customer != null) {
//            return Response.ok(customer).build();
//        }
//        throw new NotFoundException("Customer with id: " + customerId + ", was not found!");
    }
}
