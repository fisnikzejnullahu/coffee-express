package com.fisnikz.coffee_express.orders.entity;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class Order {

    private String customerId;
    private String bankAccountId;
    private List<OrderItem> items;

    public Order(String customerId, String bankAccountId, List<OrderItem> items) {
        this.customerId = customerId;
        this.bankAccountId = bankAccountId;
        this.items = items;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
