package com.fisnikz.model;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetails {

    private List<OrderItem> items;

    public OrderDetails() {
    }

    public OrderDetails(List<OrderItem> orderItems) {
        this.items = orderItems;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public double getTotalOfOrder() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(0d, Double::sum);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "items=" + items +
                '}';
    }
}