package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.customers.entity.Customer;
import com.fisnikz.coffee_express.customers.entity.UpdateCustomerRequest;
import com.fisnikz.coffee_express.logging.Logged;
import io.micrometer.core.annotation.Counted;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.validation.Valid;
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

    @PUT
    @Path("{id}")
    @RolesAllowed({"full_access"})
    public Response update(@PathParam("id") UUID customerId, @Valid UpdateCustomerRequest updateCustomerRequest){
        int updated = customerService.update(customerId, updateCustomerRequest);
        System.out.println("updated = " + updated);
        if (updated == -1) {
            return Response.status(404).build();
        }
        else if (updated == 0){
            return Response.status(400).build();
        }
        return Response.noContent().build();
    }

    @Traced
    @GET
    @Path("{customerId}")
    @RolesAllowed({"full_access", "user_data"})
    public Customer find(@PathParam("customerId") UUID customerId) {
        if (!jsonWebToken.getGroups().contains("full_access") && !customerId.equals(UUID.fromString(jsonWebToken.getClaim("customer_id")))) {
            throw new ForbiddenException();
        }
        return customerService.getCustomer(customerId);
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed({"full_access"})
    public Response delete(@PathParam("id") UUID customerId) {
        boolean deleted = customerService.delete(customerId);

        JsonObjectBuilder response = Json.createObjectBuilder()
                .add("id", customerId.toString())
                .add("success", deleted);

        return Response.ok(response.build()).build();
    }
}
