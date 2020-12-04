package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderResource {

    private UUID authorizedCustomerId;
    private UUID orderId;
    private OrderService orderService;

    public OrderResource() {
    }

    public OrderResource(UUID orderId, OrderService orderService, String authorizedCustomerId) {
        this.orderId = orderId;
        this.orderService = orderService;
        this.authorizedCustomerId = UUID.fromString(authorizedCustomerId);
    }

    @GET
    public Response find() {
        Order order = Order.findById(orderId);
        if (!order.customerId.equals(this.authorizedCustomerId)) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        if (order != null) {
            return Response.ok(order).build();
        }
        throw new NotFoundException("Order with id: " + orderId + ", was not found!");
    }

    @POST
    public Response cancel() {
        orderService.cancelOrder(orderId, "CANCELLED_FROM_USER");
        return Response.ok().build();
    }
}
