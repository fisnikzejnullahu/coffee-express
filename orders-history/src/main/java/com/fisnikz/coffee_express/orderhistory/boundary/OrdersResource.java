package com.fisnikz.coffee_express.orderhistory.boundary;

import com.fisnikz.coffee_express.logging.Logged;
import com.fisnikz.coffee_express.orderhistory.control.OrderService;
import com.fisnikz.coffee_express.orderhistory.entity.Order;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DenyAll
@Logged
public class OrdersResource {

    @Inject
    OrderService orderService;

    @Inject
    JsonWebToken jsonWebToken;

    @GET
    @Path("{orderId}")
    @RolesAllowed({"full_access", "user_data"})
    public Response find(@PathParam("orderId") String orderId) {
        Order order = Order.find("orderId", orderId).firstResult();
        if (order == null) {
            return Response.status(404).build();
        }
        if (!jsonWebToken.getGroups().contains("full_access") && !order.getCustomerId().equals(jsonWebToken.getClaim("customer_id"))) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

        return Response.ok(order).build();
    }

    @GET
    @RolesAllowed({"full_access", "user_data"})
    public Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page) {
        if (!jsonWebToken.getGroups().contains("full_access") && !customerId.equals(jsonWebToken.getClaim("customer_id"))) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        return Response.ok(orderService.ordersOfCustomer(customerId, page)).build();
    }

}
