package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.finance.control.BankAccountsService;
import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.opentracing.Traced;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
@RolesAllowed({"full_access", "manage_orders"})
public class BankAccountsResource {

    @Inject
    JsonWebToken jsonWebToken;

    @Inject
    BankAccountsService service;

    @Context
    UriInfo uriInfo;

    @Traced
    @POST
    public Response create(BankAccount account) {
        UUID id = UUID.randomUUID();
        account.id = id;
        service.create(account, jsonWebToken.getClaim("customer_id"));

        return Response
                .created(uriInfo.getRequestUriBuilder().path(BankAccountsResource.class, "find").build(id))
                .build();
    }

    @GET
    @Path("popular")
    public Response popularAccount(@QueryParam("customerId") UUID customerId){
        System.out.println("POPULAR ACCOUNT: " + customerId + ", " + jsonWebToken.getClaim("customer_id"));
        return Response.ok(service.getMostPopular(customerId, jsonWebToken.getClaim("customer_id"))).build();
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") UUID id) {
        BankAccount bankAccount = service.find(id, jsonWebToken.getClaim("customer_id"));
        if (bankAccount == null) {
            throw new NotFoundException("Account was not found!");
        }
        return Response.ok(bankAccount).build();
    }

    @DELETE
    @Path("{accountId}")
    public Response delete(@PathParam("accountId") UUID accountId) {
        int deleted = service.delete(accountId, jsonWebToken.getClaim("customer_id"));

        JsonObjectBuilder response = Json.createObjectBuilder()
                .add("id", accountId.toString());

        if (deleted != 0) {
            response.add("success", true);
        }
        else {
            response.add("success", false);
        }

        return Response.ok(response.build()).build();
    }

    @GET
    @QueryParam("customerId")
    public List<BankAccount> accountsOfCustomer(@QueryParam("customerId") UUID customerId) {
        if (jsonWebToken.getGroups().contains("full_access")) {
            return service.accountsOfCustomer(customerId, customerId.toString(), true);
        }
        return service.accountsOfCustomer(customerId, jsonWebToken.getClaim("customer_id"), false);
    }
}
