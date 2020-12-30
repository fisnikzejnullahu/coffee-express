package com.fisnikz.coffee_express.orderhistory.boundary;

import com.fisnikz.coffee_express.logging.Logged;
import com.fisnikz.coffee_express.orderhistory.control.OrderService;
import com.fisnikz.coffee_express.orderhistory.entity.Order;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;

/**
 * @author Fisnik Zejnullahu
 */
@Path("history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Logged
public class OrdersResource {

    @Inject
    OrderService orderService;

    @Inject
    JsonWebToken jsonWebToken;

    @GET
    @Path("{orderId}")
    public Response find(@PathParam("orderId") String orderId) {
        Order order = Order.find("orderId", orderId).firstResult();
        if (order == null) {
            return Response.status(404).build();
        }
        if (!order.getCustomerId().equals(jsonWebToken.getClaim("customer_id"))) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        return Response.ok(order).build();
    }

    /*
        TODO: check permission: only allow personal orders, not anyone else
        TODO: get payment information also, e.g. in html table show customer name, order details (date...) and total in money
        total of order (call finance api via rest) -> @Traced
     */
    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page) {
        if (customerId.equals(jsonWebToken.getClaim("customer_id"))) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        JsonObject response = Json.createObjectBuilder()
                .add("orders", Json.createReader(new StringReader(JsonbBuilder.create().toJson(orderService.getOrdersOfCustomer(customerId, page)))).readValue())
                .add("total-pages", orderService.totalPages())
                .build();
        return Response.ok(response).build();
    }

}
