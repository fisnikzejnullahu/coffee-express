package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.finance.control.BankAccountsService;
import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.opentracing.Traced;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
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
    public BankAccount popularAccount(@QueryParam("customerId") UUID customerId){
        return service.getMostPopular(customerId, jsonWebToken.getClaim("customer_id"));
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
        boolean deleted = service.delete(accountId, jsonWebToken.getClaim("customer_id"));
        JsonObject data = Json.createObjectBuilder()
                .add("id", accountId.toString())
                .add("success", deleted)
                .build();
        return Response.ok(data).build();
    }

    @GET
    @QueryParam("customerId")
    public List<BankAccount> accountsOfCustomer(@QueryParam("customerId") UUID customerId) {
        if (jsonWebToken.getGroups().contains("full_access")) {
            return service.accountsOfCustomer(customerId, null);
        }
        return service.accountsOfCustomer(customerId, jsonWebToken.getClaim("customer_id"));
    }
}
