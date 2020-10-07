package com.fisnikz.coffee_express.orders.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CreateOrderResponse {

    private UUID orderId;
    private OrderDetails orderDetails;
    private Order.OrderState state;

    public CreateOrderResponse() {
    }

    public CreateOrderResponse(UUID orderId, OrderDetails orderDetails, Order.OrderState state) {
        this.orderId = orderId;
        this.orderDetails = orderDetails;
        this.state = state;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order.OrderState getState() {
        return state;
    }

    public void setState(Order.OrderState state) {
        this.state = state;
    }
}
