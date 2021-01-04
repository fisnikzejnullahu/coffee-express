package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.logging.Logged;
import com.fisnikz.coffee_express.orders.control.OrderService;
import com.fisnikz.coffee_express.orders.entity.PlaceOrderRequest;
import org.eclipse.microprofile.jwt.Claim;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
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
@Logged
public class OrdersResource {

    @Context
    UriInfo uriInfo;

    @Inject
    OrderService orderService;

    @Inject
    JsonWebToken jsonWebToken;

    @POST
    @PermitAll
//    @RolesAllowed({"full_access", "manage_orders"})
    public Response place(PlaceOrderRequest placeOrderRequest) {
        System.out.println(JsonbBuilder.create().toJson(placeOrderRequest));
        UUID orderId = UUID.randomUUID();
        orderService.place(orderId, UUID.fromString(jsonWebToken.getClaim("customer_id")), placeOrderRequest);

        return Response
                .created(uriInfo.getRequestUriBuilder().path(OrdersResource.class, "find").build(orderId))
                .build();
    }

    @Path("{orderId}")
    @RolesAllowed({"full_access", "manage_orders"})
    public OrderResource find(@PathParam("orderId") UUID orderId) {
        // if user from token is admin
        if (jsonWebToken.getGroups().contains("full_access")) {
            return new OrderResource(orderId, this.orderService);
        }
        System.out.println("authorizedCustomerId = " + jsonWebToken.getClaim("customer_id"));
        return new OrderResource(orderId, this.orderService, jsonWebToken.getClaim("customer_id"));
    }

}
