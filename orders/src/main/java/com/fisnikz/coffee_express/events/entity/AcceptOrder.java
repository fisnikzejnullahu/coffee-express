package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.OrderItem;

import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class AcceptOrder extends OrderEvent {

    public List<OrderItem> orderItems;

    public AcceptOrder() {
    }

    public AcceptOrder(UUID orderId, List<OrderItem> orderItems) {
        super(orderId);
        this.orderItems = orderItems;
    }
}
