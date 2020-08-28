package com.fisnikz.coffee_express.barista.entity;

import javax.persistence.Embeddable;

/**
 * @author Fisnik Zejnullahu
 */
@Embeddable
public class OrderItem {

    public String menuItemId;
    public String menuItemName;
    public short quantity;

    public OrderItem() {
    }

    public OrderItem(String menuItemId, String menuItemName, short quantity) {
        this.menuItemId = menuItemId;
        this.menuItemName = menuItemName;
        this.quantity = quantity;
    }

}
