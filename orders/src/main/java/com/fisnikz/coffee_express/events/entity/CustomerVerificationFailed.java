package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CustomerVerificationFailed extends DomainEvent {
    public UUID customerId;
    public String message;

    public CustomerVerificationFailed() {
    }

    public CustomerVerificationFailed(UUID customerId, UUID orderId, String message) {
        super(orderId);
        this.customerId = customerId;
        this.message = message;
    }
}
