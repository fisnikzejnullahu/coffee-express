package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.MyResponseExceptionMapper;
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
@RegisterRestClient(configKey = "orders")
@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
@RegisterProvider(MyResponseExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
public interface OrdersRestClient {

    @POST
    Response place(JsonObject request);

    @GET
    @Path("{orderId}")
    Response get(@PathParam("orderId") String orderId);

}
