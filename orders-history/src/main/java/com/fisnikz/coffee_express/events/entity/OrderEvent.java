package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public abstract class OrderEvent {

    public UUID orderId;

    public OrderEvent() {
    }

    public OrderEvent(UUID orderId) {
        this.orderId = orderId;
    }
}
