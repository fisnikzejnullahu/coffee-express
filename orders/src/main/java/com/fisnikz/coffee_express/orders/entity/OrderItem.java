package com.fisnikz.coffee_express.orders.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class OrderItem {

    @ManyToOne
    public MenuItem menuItem;
    public short quantity;

    public OrderItem() {
    }

    public OrderItem(MenuItem menuItem, short quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItem.price * quantity;
    }
}