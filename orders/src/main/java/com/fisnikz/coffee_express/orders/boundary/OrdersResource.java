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

    @POST
    public Response place(Order order) {
        order.id = UUID.randomUUID();
        orderService.place(order);
        return Response
                .created(uriInfo.getRequestUriBuilder().path(OrdersResource.class, "find").build(order.id))
                .build();
    }

    @GET
    public Response all(@QueryParam("page") int page){
        return Response.ok(orderService.getOrders(page)).build();
    }

    @Path("{orderId}")
    public OrderResource find(@PathParam("orderId") UUID orderId) {
        return new OrderResource(orderId, this.orderService);
    }

    /*
        TODO: check permission: only allow personal orders, not anyone else
        TODO: get payment information also, e.g. in html table show customer name, order details (date...) and total in money
        total of order (call finance api via rest) -> @Traced
     */
    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") UUID customerId, @QueryParam("page") int page) {

        return Response.ok(orderService.getOrdersOfCustomer(customerId, page)).build();
    }

}
