package com.fisnikz.coffee_express.barista.entity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

/**
 * @author Fisnik Zejnullahu
 */
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
