package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.finance.control.BankAccountsRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Path("bank-accounts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BankAccountsResource {

    @Inject
    @RestClient
    BankAccountsRestClient bankAccountsRestClient;

    @POST
    public Response create(JsonObject account) {
        return bankAccountsRestClient.create(account);
    }

    @GET
    @Path("{id}")
    public Response find(@PathParam("id") String id){
        return bankAccountsRestClient.find(id);
    }

    @GET
    @Path("popular")
    public Response popularAccount(@QueryParam("customerId") String customerId){
        return bankAccountsRestClient.popularAccount(customerId);
    }

    @GET
    public Response accountsOfCustomer(@QueryParam("customerId") String customerId) {
        return bankAccountsRestClient.accountsOfCustomer(customerId);
    }

    @DELETE
    @Path("{accountId}")
    public Response delete(@PathParam("accountId") String accountId){
        return bankAccountsRestClient.delete(accountId);
    }

}
