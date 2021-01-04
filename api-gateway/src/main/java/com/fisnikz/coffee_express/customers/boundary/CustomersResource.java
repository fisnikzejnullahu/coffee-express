package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomersService;
import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import com.fisnikz.coffee_express.customers.entity.UpdateCustomerRequest;
import com.fisnikz.coffee_express.logging.Logged;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.fisnikz.coffee_express.identity.control.IdentityService.toBearerToken;

/**
 * @author Fisnik Zejnullahu
 */
@Path("customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
public class CustomersResource {

    @Inject
    CustomersService customersService;

    @Context
    UriInfo uriInfo;

    @CookieParam("access_token")
    String accessToken;

    @POST
    public Response create(@Valid CreateCustomerRequest createCustomerRequest) {
        String customerId = customersService.create(createCustomerRequest);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(CustomersResource.class, "find").build(customerId))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") String customerId, @Valid UpdateCustomerRequest request) {
        System.out.println(JsonbBuilder.create().toJson(request));
        return customersService.update(customerId, request);
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") String customerId) {
        return customersService.find(toBearerToken(accessToken), customerId);
    }
}
