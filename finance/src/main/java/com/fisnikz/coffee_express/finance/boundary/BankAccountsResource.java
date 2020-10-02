package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.finance.control.BankAccountsService;
import com.fisnikz.coffee_express.finance.entity.BankAccount;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BankAccountsResource {

    @Inject
    BankAccountsService service;

    @Context
    UriInfo uriInfo;

    @Traced
    @POST
    public Response create(BankAccount account) {
        System.out.println(JsonbBuilder.create().toJson(account));
        UUID id = UUID.randomUUID();
        account.id = id;
        service.create(account);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(BankAccountsResource.class, "find").build(id))
                .build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") UUID id) {
        BankAccount bankAccount = service.find(id);
        if (bankAccount == null) {
            throw new NotFoundException("Account was not found!");
        }
        return Response.ok(bankAccount).build();
    }
//
//    @GET
//    @QueryParam("customerId")
//    public Response accountsOfCustomer(@QueryParam("customerId") UUID customerId) {
//        return Response.ok().build();
//    }
}
