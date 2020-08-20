package com.fisnikz.coffee_express.orders.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;
import javax.json.JsonObject;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
public class Order extends PanacheEntityBase {

    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @Embedded
    @NotNull
    public OrderInfo orderInfo;

    @NotNull
    @Type(type = "uuid-char")
    public UUID customerId;

    @Enumerated(EnumType.STRING)
    public OrderState orderState;

    public LocalDateTime placedAt;
    public LocalDateTime acceptedAt;
    public LocalDateTime readyBy;
    public LocalDateTime startedToPrepareAt;
    public LocalDateTime finishedAt;
    public LocalDateTime cancelledAt;
    public LocalDateTime pickedUpAt;

    @Version
    public long version;

    public Order() {
    }

    public Order(UUID customerId, List<OrderItem> items) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderInfo = new OrderInfo(items);
    }

    public void place() {
        this.placedAt = LocalDateTime.now();
        this.orderState = OrderState.PLACED;
    }

    public void accept() {
        this.acceptedAt = LocalDateTime.now();
        this.orderState = OrderState.ACCEPTED;
    }

    public void start(LocalDateTime readyBy) {
        this.startedToPrepareAt = LocalDateTime.now();
        this.readyBy = readyBy;
        this.orderState = OrderState.PREPARING;
    }

    public void finish() {
        this.finishedAt = LocalDateTime.now();
        this.orderState = OrderState.READY_FOR_PICKUP;
    }

    public void cancel() {
        this.cancelledAt = LocalDateTime.now();
        this.orderState = OrderState.CANCELLED;
    }

    public void pickedUp() {
        this.pickedUpAt = LocalDateTime.now();
        this.orderState = OrderState.PICKED_UP;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", customerId=" + customerId +
                ", orderInfo=" + orderInfo +
                ", orderStatus=" + orderState +
                '}';
    }

    enum OrderState {
        PLACED,
        ACCEPTED,
        PREPARING,
        READY_FOR_PICKUP,
        CANCELLED,
        PICKED_UP
    }
}