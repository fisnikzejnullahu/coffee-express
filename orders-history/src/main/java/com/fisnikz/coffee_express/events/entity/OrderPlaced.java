package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderPlaced extends OrderEvent {
    public UUID customerId;

    public OrderPlaced() {
    }

    public OrderPlaced(UUID orderId, UUID customerId) {
        super(orderId);
        this.customerId = customerId;
    }
}
