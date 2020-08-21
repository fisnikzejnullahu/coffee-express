package com.fisnikz.coffee_express.events.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderStarted extends DomainEvent{

    private LocalDateTime readyBy;

    public OrderStarted() {
    }

    public OrderStarted(UUID orderId, LocalDateTime readyBy) {
        super(orderId);
        this.readyBy = readyBy;
    }
}
