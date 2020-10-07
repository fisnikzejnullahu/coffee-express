package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.orders.entity.MenuItem;

import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
public class MenuItemsAdded implements Event {

    private List<MenuItem> items;

    public MenuItemsAdded() {
    }

    public MenuItemsAdded(List<MenuItem> items) {
        this.items = items;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public void setItems(List<MenuItem> items) {
        this.items = items;
    }
}
