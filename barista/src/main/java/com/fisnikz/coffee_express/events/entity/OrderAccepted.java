package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.barista.entity.OrderItem;

import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderAccepted extends OrderEvent {

    public List<OrderItem> orderItems;

    public OrderAccepted() {
    }

    public OrderAccepted(UUID orderId, List<OrderItem> orderItems) {
        super(orderId);
        this.orderItems = orderItems;
    }
}

