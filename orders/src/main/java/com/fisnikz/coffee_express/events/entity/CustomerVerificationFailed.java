package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CustomerVerificationFailed extends OrderEvent {
    private UUID customerId;
    private String message;

    public CustomerVerificationFailed() {
    }

    public CustomerVerificationFailed(UUID customerId, UUID orderId, String message) {
        super(orderId);
        this.customerId = customerId;
        this.message = message;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
