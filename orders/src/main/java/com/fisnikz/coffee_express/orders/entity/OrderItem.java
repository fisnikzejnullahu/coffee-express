package com.fisnikz.coffee_express.orders.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderItem {

    public int menuItemId;
    public String menuItemName;
    public double menuItemPrice;
    public short quantity;

    public OrderItem() {
    }

    public OrderItem(int menuItemId, String menuItemName, double menuItemPrice, short quantity) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return menuItemPrice * quantity;
    }
}