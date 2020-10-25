package com.fisnikz;

import com.fisnikz.model.Order;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.json.JsonObject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(baseUri = "http://localhost:8084")
@Path("history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface OrderHistoryResourceClient {

    @GET
    JsonObject ordersOfCustomer(@QueryParam("customerId") UUID customerId, @QueryParam("page") int page);

    @GET
    @Path("{orderId}")
    Order find(@PathParam("orderId") String orderId);
}
