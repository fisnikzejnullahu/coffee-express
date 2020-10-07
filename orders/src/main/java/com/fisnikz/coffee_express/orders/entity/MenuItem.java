package com.fisnikz.coffee_express.orders.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
public class MenuItem extends PanacheEntityBase {

    @Id
    public long id;

    public String name;
    public double price;
    public boolean removed;

    public MenuItem() {
    }

    public MenuItem(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
