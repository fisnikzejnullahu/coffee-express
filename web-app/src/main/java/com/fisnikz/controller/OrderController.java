package com.fisnikz.controller;

import com.fisnikz.OrderHistoryResourceClient;
import com.fisnikz.OrderResourceClient;
import com.fisnikz.model.Cart;
import com.fisnikz.model.CreateOrderRequest;
import com.fisnikz.model.Order;
import com.fisnikz.model.PlaceOrderModel;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.fisnikz.CustomerInitializer.CUSTOMER_ID;

/**
 * @author Fisnik Zejnullahu
 */
@Controller
@Path("orders")
public class OrderController {

    @Inject
    OrderHistoryResourceClient historyResourceClient;

    @Inject
    OrderResourceClient orderResourceClient;

    @Inject
    Models models;

    @Inject
    Cart cart;
    @Inject
    PlaceOrderModel placeOrderModel;

    @GET
    @View("orders.jsp")
    public void index(@QueryParam("page") int page) {
        JsonObject data = historyResourceClient.ordersOfCustomer(CUSTOMER_ID, page);
        int totalPages = data.getInt("total-pages");
        Jsonb jsonb = JsonbBuilder.create();
        List<Order> orders = data.getJsonArray("orders").stream().map(val -> jsonb.fromJson(val.toString(), Order.class)).collect(Collectors.toList());
        models.put("current", "orders")
                .put("orders", orders)
                .put("totalPages", totalPages);
    }

    @GET
    @Path("{id}")
    @View("order-details.jsp")
    public void details(@PathParam("id") String id) {
        Order order = historyResourceClient.find(id);
        models.put("order", order);
    }

    @GET
    @Path("track/{id}")
    @View("order-track.jsp")
    public void track(@PathParam("id") String id) {
        Order order = orderResourceClient.get(id);
        System.out.println(order);
        models.put("order", order);
    }

    @POST
    @Path("place")
    public String place(@FormParam("bankAccountId") UUID bankAccountId) {
        System.out.println("PLACING " + bankAccountId);
        CreateOrderRequest request = new CreateOrderRequest(CUSTOMER_ID, bankAccountId, cart.getItems());
        System.out.println(JsonbBuilder.create().toJson(request));
        Response placedResponse = orderResourceClient.place(request);
//            //TODO: redirect error page
//        if (placedResponse.getStatus() != 201){
//        }
        String orderId = placedResponse.getLocation().toString().substring(placedResponse.getLocation().toString().lastIndexOf("/") + 1);
        return "redirect:/orders/track/" + orderId;
    }

}
