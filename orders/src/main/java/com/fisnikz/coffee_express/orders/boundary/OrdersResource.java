package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.PlaceOrderRequest;
import org.eclipse.microprofile.jwt.Claim;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.UUID;

@Path("orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@DenyAll
public class OrdersResource {

    @Context
    UriInfo uriInfo;

    @Inject
    OrderService orderService;

    @Inject
    @Claim("customer_id")
    String authorizedCustomerId;

    @POST
    @RolesAllowed({"user", "admin"})
    public Response place(PlaceOrderRequest placeOrderRequest) {
        System.out.println(JsonbBuilder.create().toJson(placeOrderRequest));
        UUID orderId = UUID.randomUUID();
        orderService.place(orderId, placeOrderRequest);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(OrdersResource.class, "find").build(orderId))
                .build();
    }

    @Path("{orderId}")
    @RolesAllowed({"user", "admin"})
    public OrderResource find(@PathParam("orderId") UUID orderId) {
        return new OrderResource(orderId, this.orderService, this.authorizedCustomerId);
    }

}
