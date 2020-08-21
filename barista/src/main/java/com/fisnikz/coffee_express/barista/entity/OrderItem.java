package com.fisnikz.coffee_express.barista.entity;

/**
 * @author Fisnik Zejnullahu
 */
public class OrderItem {

    private String menuItemId;
    private String menuItemName;
    private short quantity;

    public OrderItem() {
    }

    public OrderItem(String menuItemId, String menuItemName, short quantity) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
    }

    public String getMenuItemId() {
        return menuItemId;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public short getQuantity() {
        return quantity;
    }
}
