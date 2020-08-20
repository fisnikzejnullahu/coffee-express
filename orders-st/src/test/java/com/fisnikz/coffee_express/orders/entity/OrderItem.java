package com.fisnikz.coffee_express.orders.entity;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderItem {
    private int menuItemId;
    private String menuItemName;
    private double menuItemPrice;
    private short quantity;

    public OrderItem() {
    }

    public OrderItem(int menuItemId, String menuItemName, double menuItemPrice, short quantity) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.menuItemPrice = menuItemPrice;
        this.quantity = quantity;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public double getMenuItemPrice() {
        return menuItemPrice;
    }

    public void setMenuItemPrice(double menuItemPrice) {
        this.menuItemPrice = menuItemPrice;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}
