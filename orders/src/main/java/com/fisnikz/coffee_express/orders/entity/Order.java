package com.fisnikz.coffee_express.orders.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.json.bind.annotation.JsonbTransient;
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
    public OrderDetails orderDetails;

    @NotNull
    @Type(type = "uuid-char")
    public UUID customerId;

    @Enumerated(EnumType.STRING)
    public OrderState orderState;

    @Embedded
    public PaymentInformation paymentInformation;

    public LocalDateTime placedAt;
    public LocalDateTime acceptedAt;
    public LocalDateTime readyBy;
    public LocalDateTime finishedAt;
    public LocalDateTime cancelledAt;
    public LocalDateTime pickedUpAt;

    @JsonbTransient
    @Version
    public long version;

    public Order() {
    }

    public Order(UUID customerId, List<OrderItem> items) {
        this.id = UUID.randomUUID();
        this.customerId = customerId;
        this.orderDetails = new OrderDetails(items);
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
                ", orderInfo=" + orderDetails +
                ", orderStatus=" + orderState +
                '}';
    }

    public enum OrderState {
        PLACED,
        ACCEPTED,
        PREPARING,
        READY_FOR_PICKUP,
        CANCELLED,
        PICKED_UP
    }
}
