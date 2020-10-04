package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.OrderDetails;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderPlaced extends OrderEvent {

    private OrderDetails orderDetails;
    private UUID customerId;
    private UUID bankAccountId;

    public OrderPlaced() {
    }

    public OrderPlaced(UUID orderId, OrderDetails orderDetails, UUID customerId, UUID bankAccountId) {
        super(orderId);
        this.orderDetails = orderDetails;
        this.customerId = customerId;
        this.bankAccountId = bankAccountId;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(UUID bankAccountId) {
        this.bankAccountId = bankAccountId;
    }
}