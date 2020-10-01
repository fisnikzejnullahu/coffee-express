package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.enterprise.context.Dependent;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Dependent
public class OrderResource {

    private UUID orderId;
    private OrderService orderService;

    public OrderResource() {
    }

    public OrderResource(UUID orderId, OrderService orderService) {
        this.orderId = orderId;
        this.orderService = orderService;
    }

    @GET
    public Response find(){
        Order order = Order.findById(orderId);
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
