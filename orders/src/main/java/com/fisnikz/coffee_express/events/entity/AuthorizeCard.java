package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.PaymentInformation;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizeCard extends OrderEvent {

    public UUID customerId;
    public double amount;
    public PaymentInformation paymentInformation;

    public AuthorizeCard() {
    }

    public AuthorizeCard(UUID orderId, UUID customerId, double amount, PaymentInformation paymentInformation) {
        super(orderId);
        this.customerId = customerId;
        this.amount = amount;
        this.paymentInformation = paymentInformation;
    }
}
