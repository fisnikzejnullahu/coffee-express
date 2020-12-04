package com.fisnikz.coffee_express.orderhistory.boundary;

import com.fisnikz.coffee_express.orderhistory.control.OrderService;
import com.fisnikz.coffee_express.orderhistory.entity.Order;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.jwt.Claim;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.StringReader;
import java.util.List;
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

    @Inject
    @Claim("customer_id")
    String authorizedCustomerId;

    @GET
    @Path("{orderId}")
    public Order find(@PathParam("orderId") String orderId) {
        Order order = Order.findById(orderId);
        if (!order.getCustomerId().equals(authorizedCustomerId)) {
            throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());
        }
        return order;
    }

    /*
        TODO: check permission: only allow personal orders, not anyone else
        TODO: get payment information also, e.g. in html table show customer name, order details (date...) and total in money
        total of order (call finance api via rest) -> @Traced
     */
    @GET
    public Response ordersOfCustomer(@QueryParam("customerId") String customerId, @QueryParam("page") int page) {
        if (customerId.equals(authorizedCustomerId)) {
            throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());
        }
        JsonObject response = Json.createObjectBuilder()
                .add("orders", Json.createReader(new StringReader(JsonbBuilder.create().toJson(orderService.getOrdersOfCustomer(customerId, page)))).readValue())
                .add("total-pages", orderService.totalPages())
                .build();
        return Response.ok(response).build();
    }

}
