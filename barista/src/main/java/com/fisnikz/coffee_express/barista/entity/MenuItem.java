package com.fisnikz.coffee_express.barista.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
public class MenuItem extends PanacheEntity {

    public String name;
    public double price;
    public boolean removed;

    public MenuItem() {
    }

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
