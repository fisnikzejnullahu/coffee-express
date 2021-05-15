package com.fisnikz.coffee_express.orderhistory.entity;

import io.quarkus.mongodb.panache.MongoEntity;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import org.bson.codecs.pojo.annotations.BsonIgnore;

import javax.json.bind.annotation.JsonbDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author Fisnik Zejnullahu
 */
@MongoEntity(collection = "ORDERS")
public class Order extends PanacheMongoEntity {

    private String orderId;
    private String customerId;
    private OrderDetails orderDetails;
    private OrderState orderState;
    private boolean paid;
    private String bankAccountId;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime placedAt;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime acceptedAt;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime startedAt;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime readyBy;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime finishedAt;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime cancelledAt;

    @JsonbDateFormat("d MMM y, HH:mm:ss")
    private LocalDateTime pickedUpAt;
    private String cancelledReason;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(OrderDetails orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public LocalDateTime getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(LocalDateTime placedAt) {
        this.placedAt = placedAt;
    }

    public LocalDateTime getAcceptedAt() {
        return acceptedAt;
    }

    public void setAcceptedAt(LocalDateTime acceptedAt) {
        this.acceptedAt = acceptedAt;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getReadyBy() {
        return readyBy;
    }

    public void setReadyBy(LocalDateTime readyBy) {
        this.readyBy = readyBy;
    }

    public LocalDateTime getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(LocalDateTime finishedAt) {
        this.finishedAt = finishedAt;
    }

    public LocalDateTime getCancelledAt() {
        return cancelledAt;
    }

    public void setCancelledAt(LocalDateTime cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public LocalDateTime getPickedUpAt() {
        return pickedUpAt;
    }

    public void setPickedUpAt(LocalDateTime pickedUpAt) {
        this.pickedUpAt = pickedUpAt;
    }

    public String getCancelledReason() {
        return cancelledReason;
    }

    public void setCancelledReason(String cancelledReason) {
        this.cancelledReason = cancelledReason;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    // if order has not yet been start preparing, that just return empty string
    @BsonIgnore
    public String getEstimatedPreparationTime() {
        return this.getStartedAt() != null
                ? ChronoUnit.MINUTES.between(this.getStartedAt(), this.getReadyBy()) + " MINUTES"
                : "";
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
