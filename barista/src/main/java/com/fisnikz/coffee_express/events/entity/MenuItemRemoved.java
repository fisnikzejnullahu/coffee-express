package com.fisnikz.coffee_express.events.entity;

/**
 * @author Fisnik Zejnullahu
 */
public class MenuItemRemoved implements Event {

    private long menuItemId;

    public MenuItemRemoved() {
    }

    public MenuItemRemoved(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }
}
