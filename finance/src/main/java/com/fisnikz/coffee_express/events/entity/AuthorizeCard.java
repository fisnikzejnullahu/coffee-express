package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.finance.entity.PaymentInformation;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizeCard extends OrderEvent {

    public UUID bankAccountId;
    public double amount;

    public AuthorizeCard() {
    }

    public AuthorizeCard(UUID orderId, UUID bankAccountId, double amount) {
        super(orderId);
        this.bankAccountId = bankAccountId;
        this.amount = amount;
    }
}
