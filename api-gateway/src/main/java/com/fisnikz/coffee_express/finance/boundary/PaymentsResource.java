package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.finance.control.PaymentsRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Path("payments")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentsResource {

    @Inject
    @RestClient
    PaymentsRestClient paymentsRestClient;

    @GET
    public Response paymentsOfCustomer(@QueryParam("customerId") String customerId){
        return paymentsRestClient.paymentsOfCustomer(customerId);
    }
}
