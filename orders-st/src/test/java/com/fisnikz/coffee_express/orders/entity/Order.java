package com.fisnikz.coffee_express.orders.entity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class Order {

    private String customerId;
    private List<OrderItem> items;
    private long cardNumber;
    private LocalDate expirationDate;
    private short cvc;

    public Order(String customerId, List<OrderItem> items, long cardNumber, LocalDate expirationDate, short cvc) {
        this.customerId = customerId;
        this.items = items;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvc = cvc;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public short getCvc() {
        return cvc;
    }

    public void setCvc(short cvc) {
        this.cvc = cvc;
    }
}
