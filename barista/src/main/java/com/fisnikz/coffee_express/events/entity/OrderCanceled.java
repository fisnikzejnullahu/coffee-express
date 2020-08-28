package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderCanceled extends OrderEvent {
    public OrderCanceled() {
    }

    public OrderCanceled(UUID orderId) {
        super(orderId);
    }
}
