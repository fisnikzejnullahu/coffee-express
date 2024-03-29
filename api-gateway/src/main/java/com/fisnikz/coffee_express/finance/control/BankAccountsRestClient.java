package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.ResponseWebApplicationExceptionMapper;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.ConnectException;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "finance")
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
//@RegisterProvider(ResponseWebApplicationExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
@Retry(maxRetries = 1, retryOn = ConnectException.class,abortOn = CircuitBreakerOpenException.class)
@CircuitBreaker(requestVolumeThreshold = 6, failureRatio = 0.5, delay = 3000L, successThreshold = 2, skipOn = WebApplicationException.class)
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
