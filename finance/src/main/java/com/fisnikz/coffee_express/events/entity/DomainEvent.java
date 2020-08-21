package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public abstract class DomainEvent {

    public UUID orderId;

    public DomainEvent() {
    }

    public DomainEvent(UUID orderId) {
        this.orderId = orderId;
    }
}
