package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.control.OrdersResourceClient;
import com.fisnikz.coffee_express.orders.control.RequestJsonBuider;
import com.fisnikz.coffee_express.orders.entity.Order;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderSystem {

    private OrdersResourceClient client;
    private RequestJsonBuider jsonBuilder;

    public OrderSystem() {
        URI uri = URI.create("http://localhost:8081/");
        this.client = RestClientBuilder.
                newBuilder().
                baseUri(uri).
                build(OrdersResourceClient.class);

        this.jsonBuilder = new RequestJsonBuider();
    }

    public URI placeOrder(Order order) {
        Response response = sendRequest(order);
        verifySuccess(response);
        return response.getLocation();
    }

    public JsonObject getOrder(String orderId) {
        Response response = this.client.get(orderId);
        verifySuccess(response);
        return response.readEntity(JsonObject.class);
    }

    public Response sendRequest(Order order) {
        JsonObject requestBody = this.jsonBuilder.toJson(order);
        System.out.println("REQUEST BODY: " + requestBody);
        return this.client.place(requestBody);
    }

    private void verifySuccess(Response response) {
        verifyStatus(response, Response.Status.Family.SUCCESSFUL);
    }

    private void verifyStatus(Response response, Response.Status.Family statusFamily) {
        if (response.getStatusInfo().getFamily() != statusFamily)
            throw new AssertionError("Status was not successful: " + response.getStatus());
    }
}
