package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.customers.entity.Customer;
import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.System.Logger.Level.INFO;

/**
 * @author Fisnik Zejnullahu
 */
@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DenyAll
@Logged
public class CustomersResource {

    @Inject
    CustomerService customerService;

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    Logger LOG;

    @GET
    @RolesAllowed({"full_access"})
    public Response all(){
        return Response.ok(Customer.listAll()).build();
    }

    @Counted
    @POST
    @PermitAll
    public Response create(Customer customer, @Context UriInfo uriInfo){
        UUID customerId = customerService.create(customer);
        return Response
                .created(uriInfo.getRequestUriBuilder().path(CustomersResource.class, "find").build(customerId))
                .build();
    }

    @Traced
    @GET
    @Path("{customerId}")
    @RolesAllowed({"full_access", "manage_orders"})
    public Customer find(@PathParam("customerId") UUID customerId) {
        if (!jsonWebToken.getGroups().contains("full_access") && !customerId.equals(UUID.fromString(jsonWebToken.getClaim("customer_id")))) {
            throw new ForbiddenException();
        }
        return customerService.getCustomer(customerId);
    }
}
