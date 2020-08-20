package com.fisnikz.coffee_express.orders.control;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OrdersResourceClient {

    @POST
    Response place(JsonObject requestBody);

    @GET
    @Path("{orderId}")
    Response get(@PathParam("orderId") String orderId);
}