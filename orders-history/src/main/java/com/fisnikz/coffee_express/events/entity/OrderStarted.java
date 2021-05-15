package com.fisnikz.coffee_express.events.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderStarted extends OrderEvent {

    public LocalDateTime startedAt;
    public LocalDateTime readyBy;

    public OrderStarted() {
    }

    public OrderStarted(UUID orderId, LocalDateTime startedAt, LocalDateTime readyBy) {
        super(orderId);
        this.startedAt = startedAt;
        this.readyBy = readyBy;
    }
}
