package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderFailedCancel extends OrderEvent {
    public OrderFailedCancel() {
    }

    public OrderFailedCancel(UUID orderId) {
        super(orderId);
    }
}
