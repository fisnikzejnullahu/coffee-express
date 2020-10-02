package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CardAuthorizationFailed extends OrderEvent {

    public String message;

    public CardAuthorizationFailed() {
    }

    public CardAuthorizationFailed(UUID orderId, String message) {
        super(orderId);
        this.message = message;
    }
}
