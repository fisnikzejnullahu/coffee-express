package com.fisnikz.coffee_express.orders.entity;

import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class CreateOrderRequest {

    private UUID customerId;
    private UUID bankAccountId;
    private List<_OrderItem> items;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(UUID customerId, UUID bankAccountId, List<_OrderItem> items) {
        this.customerId = customerId;
        this.bankAccountId = bankAccountId;
        this.items = items;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(UUID bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public List<_OrderItem> getItems() {
        return items;
    }

    public void setItems(List<_OrderItem> items) {
        this.items = items;
    }

    public static class _OrderItem {

        private long menuItemId;
        private short quantity;

        public _OrderItem() {
        }

        public _OrderItem(long menuItemId, short quantity) {
            this.menuItemId = menuItemId;
            this.quantity = quantity;
        }

        public long getMenuItemId() {
            return menuItemId;
        }

        public short getQuantity() {
            return quantity;
        }

        public void setQuantity(short quantity) {
            this.quantity = quantity;
        }

        public void setMenuItemId(long menuItemId) {
            this.menuItemId = menuItemId;

        }
    }
}
