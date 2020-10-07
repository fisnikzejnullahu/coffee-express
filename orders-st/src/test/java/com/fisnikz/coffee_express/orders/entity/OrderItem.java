package com.fisnikz.coffee_express.orders.entity;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderItem {
    private int menuItemId;
    private short quantity;

    public OrderItem() {
    }

    public OrderItem(int menuItemId, short quantity) {
        this.menuItemId = menuItemId;
        this.quantity = quantity;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}
