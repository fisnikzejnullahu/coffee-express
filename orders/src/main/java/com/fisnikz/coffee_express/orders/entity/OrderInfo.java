package com.fisnikz.coffee_express.orders.entity;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import java.util.List;

@Embeddable
public class OrderInfo {

    @ElementCollection(fetch = FetchType.EAGER)
    public List<OrderItem> items;

    public OrderInfo() {
    }

    public OrderInfo(List<OrderItem> orderItems) {
        this.items = orderItems;
    }

    public double getTotalOfOrder() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(0d, Double::sum);
    }
}