package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.MyResponseExceptionMapper;
import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "finance")
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
@RegisterProvider(MyResponseExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
public interface BankAccountsRestClient {

    @POST
    Response create(JsonObject account);

    @GET
    @Path("{id}")
    Response find(@PathParam("id") String id);

    @GET
    @Path("popular")
    Response popularAccount(@QueryParam("customerId") String customerId);

    @GET
    Response accountsOfCustomer(@QueryParam("customerId") String customerId);

    @DELETE
    @Path("{accountId}")
    Response delete(@PathParam("accountId") String accountId);

}
