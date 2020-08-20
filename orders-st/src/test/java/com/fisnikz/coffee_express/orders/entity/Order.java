package com.fisnikz.coffee_express.orders.entity;

import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class Order {

    private String customerId;
    private List<OrderItem> items;

    public Order(String customerId, List<OrderItem> items) {
        this.customerId = customerId;
        this.items = items;
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
}
