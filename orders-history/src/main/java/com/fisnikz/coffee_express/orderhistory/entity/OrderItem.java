package com.fisnikz.coffee_express.orderhistory.entity;

import javax.json.JsonObject;

public class OrderItem {

    private MenuItem menuItem;
    private short quantity;

    public OrderItem() {
    }

    public OrderItem(MenuItem menuItem, short quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItem.getPrice() * quantity;
    }
}