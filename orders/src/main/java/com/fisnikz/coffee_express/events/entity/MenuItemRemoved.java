package com.fisnikz.coffee_express.events.entity;

/**
 * @author Fisnik Zejnullahu
 */
public class MenuItemRemoved implements Event {

    private int menuItemId;

    public MenuItemRemoved() {
    }

    public MenuItemRemoved(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }
}
