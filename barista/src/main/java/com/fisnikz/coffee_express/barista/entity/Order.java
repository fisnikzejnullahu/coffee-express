package com.fisnikz.coffee_express.barista.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
//@NamedQueries({
//    @NamedQuery(name = "Order.pendingOrders", query = "from Order where readyBy IS NULL"),
//    @NamedQuery(name = "Order.unfinishedOrders", query = "from Order where readyBy IS NOT NULL and finishedAt IS NULL"),
//})
public class Order extends PanacheEntityBase {

    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @ElementCollection(fetch = FetchType.EAGER)
    public List<OrderItem> items;

    public LocalDateTime acceptedAt;
    public LocalDateTime startedAt;
    public LocalDateTime finishedAt;
    public LocalDateTime readyBy;

    public Order() {
    }

    public Order(UUID id, List<OrderItem> items) {
        this.id = id;
        this.items = items;
    }

    public void accept() {
        this.acceptedAt = LocalDateTime.now();
    }

    public void start(LocalDateTime readyBy) {
        this.startedAt = LocalDateTime.now();
        this.readyBy = readyBy;
    }

    public void finish() {
        this.finishedAt = LocalDateTime.now();
    }


}
