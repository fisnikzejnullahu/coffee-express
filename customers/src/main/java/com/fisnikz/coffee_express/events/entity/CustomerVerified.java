package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CustomerVerified extends DomainEvent {
    public UUID customerId;

    public CustomerVerified() {
    }

    public CustomerVerified(UUID customerId, UUID orderId) {
        super(orderId);
        this.customerId = customerId;
    }
}
