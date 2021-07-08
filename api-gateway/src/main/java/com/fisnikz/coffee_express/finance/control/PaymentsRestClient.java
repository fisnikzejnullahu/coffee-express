package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.ResponseWebApplicationExceptionMapper;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.exceptions.CircuitBreakerOpenException;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "finance")
@Path("payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
@RegisterProvider(ResponseWebApplicationExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
@Retry(maxRetries = 1, abortOn = CircuitBreakerOpenException.class)
@CircuitBreaker(requestVolumeThreshold = 6, failureRatio = 0.5, delay = 3000L, successThreshold = 2)
public interface PaymentsRestClient {

    @GET
    Response paymentsOfCustomer(@QueryParam("customerId") String customerId);

}
