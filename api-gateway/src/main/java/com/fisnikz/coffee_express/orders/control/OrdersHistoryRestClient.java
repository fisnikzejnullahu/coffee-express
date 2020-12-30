package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.AuthorizationHeaderClientRequestCheck;
import com.fisnikz.coffee_express.MyResponseExceptionMapper;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(configKey = "orders-history")
@Path("history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterClientHeaders
@RegisterProvider(MyResponseExceptionMapper.class)
@RegisterProvider(AuthorizationHeaderClientRequestCheck.class)
public interface OrdersHistoryRestClient {

    @GET
    Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page);

    @GET
    @Path("{orderId}")
    Response find(@PathParam("orderId") String orderId);

}
