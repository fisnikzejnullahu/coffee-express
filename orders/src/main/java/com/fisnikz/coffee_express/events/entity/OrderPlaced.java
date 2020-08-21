package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.OrderItem;

import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderPlaced extends DomainEvent {
    public UUID customerId;

    public OrderPlaced() {
    }

    public OrderPlaced(UUID orderId, UUID customerId) {
        super(orderId);
        this.customerId = customerId;
    }
}
