package com.fisnikz.coffee_express.events.entity;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderStarted extends OrderEvent {

    private LocalDateTime readyBy;

    public OrderStarted() {
    }

    public OrderStarted(UUID orderId, LocalDateTime readyBy) {
        super(orderId);
        this.readyBy = readyBy;
    }

    public LocalDateTime getReadyBy() {

        return readyBy;
    }

    public void setReadyBy(LocalDateTime readyBy) {
        this.readyBy = readyBy;
    }
}
