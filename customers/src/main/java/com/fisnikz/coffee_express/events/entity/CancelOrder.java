package com.fisnikz.coffee_express.events.entity;

import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CancelOrder extends OrderCommand{
    public CancelOrder() {
    }

    public CancelOrder(UUID orderId) {
        super(orderId);
    }
}
