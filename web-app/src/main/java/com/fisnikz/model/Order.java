package com.fisnikz.model;

import javax.json.JsonObject;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * @author Fisnik Zejnullahu
 */
public class Order {

    private String orderId;
    private String customerId;
    private OrderDetails orderDetails;
    private OrderState orderState;
    private boolean paid;
    private String bankAccountId;
    private LocalDateTime placedAt;
    private LocalDateTime acceptedAt;
    private LocalDateTime readyBy;
    private LocalDateTime finishedAt;
    private LocalDateTime cancelledAt;
    private LocalDateTime pickedUpAt;
    private String cancelledReason;

    public Order() {
    }

    public Order(String orderId, String customerId, OrderDetails orderDetails, OrderState orderState, boolean paid, String bankAccountId, LocalDateTime placedAt, LocalDateTime acceptedAt, LocalDateTime readyBy, LocalDateTime finishedAt, LocalDateTime cancelledAt, LocalDateTime pickedUpAt, String cancelledReason) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDetails = orderDetails;
        this.orderState = orderState;
        this.paid = paid;
        this.bankAccountId = bankAccountId;
        this.placedAt = placedAt;
        this.acceptedAt = acceptedAt;
        this.readyBy = readyBy;
        this.finishedAt = finishedAt;
        this.cancelledAt = cancelledAt;
        this.pickedUpAt = pickedUpAt;
        this.cancelledReason = cancelledReason;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return orderId;
    }

    public void setId(String orderId) {
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

    public String formatTime(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.UK);
        return time.format(formatter);
    }

    public String formatDate(LocalDateTime time) {
        if (time == null) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(Locale.UK);
        return time.format(formatter);
    }

    public String getPreparationTime() {
        if (this.placedAt == null || this.finishedAt == null){
            return "";
        }
        var duration = Duration.between(this.placedAt, this.finishedAt);
        var secondsPart = duration.toSecondsPart();
        var mins = duration.toMinutes();

        if (mins > 0) {
            var time = Math.round(mins + (secondsPart / 60.0));
            return time + " minutes";
        }
        return secondsPart + " seconds";
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", orderDetails=" + orderDetails +
                ", orderState=" + orderState +
                ", paid=" + paid +
                ", bankAccountId='" + bankAccountId + '\'' +
                ", placedAt=" + placedAt +
                ", acceptedAt=" + acceptedAt +
                ", readyBy=" + readyBy +
                ", finishedAt=" + finishedAt +
                ", cancelledAt=" + cancelledAt +
                ", pickedUpAt=" + pickedUpAt +
                ", cancelledReason='" + cancelledReason + '\'' +
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
