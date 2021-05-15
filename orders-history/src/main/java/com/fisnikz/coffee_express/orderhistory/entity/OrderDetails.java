package com.fisnikz.coffee_express.orderhistory.entity;

import java.math.RoundingMode;
import java.text.NumberFormat;
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

    public String getTotalOfOrder() {
        double total = items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(0d, Double::sum);

        return formatAndRound(total);
    }

    private String formatAndRound(double num) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(2);
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);

        return nf.format(num);
    }
}