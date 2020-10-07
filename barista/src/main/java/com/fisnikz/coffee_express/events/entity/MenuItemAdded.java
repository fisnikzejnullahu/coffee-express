package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.barista.entity.MenuItem;

/**
 * @author Fisnik Zejnullahu
 */
public class MenuItemAdded implements Event {

    private MenuItem item;

    public MenuItemAdded() {
    }

    public MenuItemAdded(MenuItem item) {
        this.item = item;
    }

    public MenuItem getItem() {
        return item;
    }

    public void setItem(MenuItem item) {
        this.item = item;
    }
}
