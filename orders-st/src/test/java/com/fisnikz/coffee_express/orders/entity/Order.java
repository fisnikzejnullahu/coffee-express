package com.fisnikz.coffee_express.orders.entity;

import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class Order {

    private String customerId;
    private String bankAccountId;
    private List<OrderItem> items;

    public Order(String bankAccountId, List<OrderItem> items) {
        this.bankAccountId = bankAccountId;
        this.items = items;
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
