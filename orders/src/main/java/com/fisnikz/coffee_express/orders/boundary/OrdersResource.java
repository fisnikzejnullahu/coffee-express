package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.Order;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
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

    @Counted
    @POST
    public Response place(Order order) {
        order.id = UUID.randomUUID();

        orderService.place(order);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(OrdersResource.class, "find").build(order.id))
                .build();
    }

    @GET
    @Path("{orderId}")
    public Response find(@PathParam("orderId") UUID orderId) {
        Order order = Order.findById(orderId);
        if (order != null) {
            return Response.ok(order).build();
        }
        throw new NotFoundException("Order with id: " + orderId + ", was not found!");
    }

    @POST
    @Path("{orderId}")
    public Response cancel(@PathParam("orderId") UUID orderId) {
        orderService.cancelOrder(orderId);
        return Response.ok().build();
    }

    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") UUID customerId, @QueryParam("page") int page) {
        // TODO: check permission: only allow personal orders, not anyone else
        return Response.ok(orderService.getOrdersOfCustomer(customerId, page)).build();
    }

}
