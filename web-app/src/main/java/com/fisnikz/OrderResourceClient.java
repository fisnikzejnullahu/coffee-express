package com.fisnikz;

import com.fisnikz.model.CreateOrderRequest;
import com.fisnikz.model.Order;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(baseUri = "http://localhost:8088")
@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(LowerCaseWithDashesJsonbNamingStrategy.class)
public interface OrderResourceClient {

    @POST
    Response place(CreateOrderRequest request);

    @GET
    @Path("{orderId}")
    Order get(@PathParam("orderId") String orderId);
}
