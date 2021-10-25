package com.fisnikz.coffee_express.orders.control;

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
    private RequestJsonBuilder jsonBuilder;

    public OrderSystem() {
        URI uri = URI.create("http://localhost:8085/api/v1/");
        this.client = RestClientBuilder
                .newBuilder()
                .baseUri(uri)
                .build(OrdersResourceClient.class);

        this.jsonBuilder = new RequestJsonBuilder();
    }

    public URI placeOrder(Order order, String accessToken) {
        Response response = sendRequest(order, accessToken);
        System.out.println("Response status code: " +  response.getStatus());
        verifySuccess(response);
        return response.getLocation();
    }

    public void placeInvalidOrder(Order order) {
        Response response = sendRequest(order, "");
        verifyClientError(response);
    }

    public JsonObject getOrder(String orderId) {
        Response response = this.client.get(orderId);
        verifySuccess(response);
        return response.readEntity(JsonObject.class);
    }

    public Response sendRequest(Order order, String accessToken) {
        JsonObject requestBody = this.jsonBuilder.toJson(order);
        System.out.println("REQUEST BODY: " + requestBody);
        return this.client.place(requestBody, accessToken);
    }

    private void verifySuccess(Response response) {
        verifyStatus(response, Response.Status.Family.SUCCESSFUL);
    }

    private void verifyClientError(Response response) {
        verifyStatus(response, Response.Status.Family.CLIENT_ERROR);
    }

    private void verifyStatus(Response response, Response.Status.Family statusFamily) {
        if (response.getStatusInfo().getFamily() != statusFamily)
            throw new AssertionError("Status was not successful: " + response.getStatus());
    }
}
