package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrdersHistoryRestClient;
import com.fisnikz.coffee_express.orders.control.OrdersRestClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Inject
    @RestClient
    OrdersRestClient ordersRestClient;

    @Inject
    @RestClient
    OrdersHistoryRestClient ordersHistoryRestClient;

    @POST
    public Response place(JsonObject body) {
        return ordersRestClient.place(body);
    }

    @GET
    @Path("track/{id}")
    public Response track(@PathParam("id") String orderId) {
        return ordersRestClient.get(orderId);
    }

    @GET
    @Path("{id}")
    public Response getOrderDetails(@PathParam("id") String orderId) {
        return ordersHistoryRestClient.find(orderId);
    }

    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page) {
        return ordersHistoryRestClient.ordersOfCustomer(customerId, page);
    }
}
