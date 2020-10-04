package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.OrderItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderAccepted extends OrderEvent {

    public LocalDateTime acceptedAt;

    public OrderAccepted() {
    }

    public OrderAccepted(UUID orderId, LocalDateTime acceptedAt) {
        super(orderId);
        this.acceptedAt = acceptedAt;
    }
}

