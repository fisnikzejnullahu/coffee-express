package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CardAuthorized extends OrderEvent {

    public CardAuthorized() {
    }

    public CardAuthorized(UUID orderId) {
        super(orderId);
    }
}
