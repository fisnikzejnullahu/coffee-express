package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.CreateOrderRequest;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Context
    UriInfo uriInfo;

    @Inject
    OrderService orderService;

    @POST
    public Response place(CreateOrderRequest createOrderRequest) {
        UUID orderId = UUID.randomUUID();
        orderService.place(orderId, createOrderRequest);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(OrdersResource.class, "find").build(orderId))
                .build();
    }

    @Path("{orderId}")
    public OrderResource find(@PathParam("orderId") UUID orderId) {
        return new OrderResource(orderId, this.orderService);
    }

}
