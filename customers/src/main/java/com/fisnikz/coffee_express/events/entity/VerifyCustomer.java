package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class VerifyCustomer extends OrderCommand {
    private UUID customerId;

    public VerifyCustomer() {
    }

    public VerifyCustomer(UUID orderId, UUID customerId) {
        super(orderId);
        this.customerId = customerId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
