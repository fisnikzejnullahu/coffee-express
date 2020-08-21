package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.PaymentInformation;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class AuthorizeCard extends DomainEvent {

    private UUID customerId;
    private PaymentInformation paymentInformation;

    public AuthorizeCard() {
    }

    public AuthorizeCard(UUID orderId, UUID customerId, PaymentInformation paymentInformation) {
        super(orderId);
        this.customerId = customerId;
        this.paymentInformation = paymentInformation;
    }
}
