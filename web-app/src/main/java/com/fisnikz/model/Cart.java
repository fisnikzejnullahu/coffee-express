package com.fisnikz.model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Fisnik Zejnullahu
 */
@Named("cart")
@SessionScoped
@Model
public class Cart implements Serializable {

    private List<OrderItem> items;

    @PostConstruct
    public void init() {
        this.items = new ArrayList<>();
    }

    public void add(MenuItem item, short quantity) {
        this.items.stream()
                .filter((theItem) -> theItem.getMenuItem().equals(item)).findFirst()
                .ifPresentOrElse(
                        theItem -> setQuantity(theItem, quantity),
                        () -> addItem(item, quantity));
    }

    private void addItem(MenuItem item, short quantity) {
        this.items.add(new OrderItem(item, quantity));
    }

    private void setQuantity(OrderItem item, short quantity) {
        item.setQuantity((short) (item.getQuantity() + quantity));
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public void remove(long menuItemId) {
        this.items.stream()
                .filter(item -> item.getMenuItem().getId() == menuItemId)
                .findFirst()
                .ifPresent(this.items::remove);
    }

    public double getTotalPrice() {
        return items.stream()
                .map(OrderItem::getTotalPrice)
                .reduce(0d, Double::sum);
    }
}
