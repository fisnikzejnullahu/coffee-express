package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.MyResponseExceptionMapper;
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
@RegisterProvider(MyResponseExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
public interface PaymentsRestClient {

    @GET
    Response paymentsOfCustomer(@QueryParam("customerId") String customerId);

}
