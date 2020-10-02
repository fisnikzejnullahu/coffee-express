package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.OrderDetails;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderCreated extends OrderEvent {

    public OrderDetails orderDetails;
    public UUID customerId;
    public UUID bankAccountId;

    public OrderCreated() {
    }

    public OrderCreated(UUID orderId, OrderDetails orderDetails, UUID customerId, UUID bankAccountId) {
        super(orderId);
        this.orderDetails = orderDetails;
        this.customerId = customerId;
        this.bankAccountId = bankAccountId;
    }
}