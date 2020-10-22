package com.fisnikz.coffee_express.orderhistory.boundary;

import com.fisnikz.coffee_express.orderhistory.control.OrderService;
import com.fisnikz.coffee_express.orderhistory.entity.Order;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Path("history")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @Inject
    OrderService orderService;

    @GET
    @Path("{orderId}")
    public Order find(@PathParam("orderId") String orderId) {
        return Order.find("orderId", orderId).firstResult();
    }

    /*
        TODO: check permission: only allow personal orders, not anyone else
        TODO: get payment information also, e.g. in html table show customer name, order details (date...) and total in money
        total of order (call finance api via rest) -> @Traced
     */
    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page) {
        return Response.ok(orderService.getOrdersOfCustomer(customerId, page)).build();
    }

}
