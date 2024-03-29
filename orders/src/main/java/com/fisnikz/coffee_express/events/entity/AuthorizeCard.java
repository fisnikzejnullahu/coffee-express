package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizeCard extends OrderCommand {

    private UUID bankAccountId;
    private UUID customerId;
    private double amount;

    public AuthorizeCard() {
    }

    public AuthorizeCard(UUID orderId, UUID bankAccountId, UUID customerId, double amount) {
        super(orderId);
        this.bankAccountId = bankAccountId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(UUID bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }
}
