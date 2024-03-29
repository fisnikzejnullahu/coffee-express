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

    @NotNull
    @Type(type = "uuid-char")
    public UUID bankAccountId;

    @Enumerated(EnumType.STRING)
    public OrderState orderState;

    public String cancelledReason;

    @JsonbTransient
    @Version
    public long version;


    public Order() {
    }

    public Order(UUID orderId, UUID customerId, UUID bankAccountId, OrderDetails orderDetails) {
        this.id = orderId;
        this.customerId = customerId;
        this.bankAccountId = bankAccountId;
        this.orderDetails = orderDetails;
    }

    public void place() {
        this.orderState = OrderState.PLACED;
    }

    public void accept() {
        this.orderState = OrderState.ACCEPTED;
    }

    public void start() {
        this.orderState = OrderState.PREPARING;
    }

    public void finish() {
        this.orderState = OrderState.READY_FOR_PICKUP;
    }

    public void cancel(String cancelledReason) {
        this.cancelledReason = cancelledReason;
        this.orderState = OrderState.CANCELLED;
    }

    public void pickedUp() {
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
