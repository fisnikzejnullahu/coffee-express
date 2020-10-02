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

/**
 * @author Fisnik Zejnullahu
 */
@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrdersResource {

    @GET
    @Path("{orderId}")
    public Order find(@PathParam("orderId") String orderId) {
        return Order.find("orderId", orderId).firstResult();
    }
}
