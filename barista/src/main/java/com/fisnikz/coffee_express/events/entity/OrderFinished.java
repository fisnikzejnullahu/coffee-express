package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderFinished extends OrderEvent {
    public OrderFinished() {
    }

    public OrderFinished(UUID orderId) {
        super(orderId);
    }
}
