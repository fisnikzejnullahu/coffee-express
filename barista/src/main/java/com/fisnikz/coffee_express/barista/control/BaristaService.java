package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.entity.Order;
import com.fisnikz.coffee_express.barista.boundary.BaristaCommandService;
import com.fisnikz.coffee_express.barista.entity.OrderItem;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class BaristaService {

    @Inject
    BaristaCommandService commandService;

    @Inject
    Event<Order> event;

    /*
        This method should notify barista's screen for a new order
     */
    public void acceptOrder(UUID orderId, List<OrderItem> orderItems) {
        Order order = new Order(orderId, orderItems);
        order.accept();
        order.persist();
        event.fire(order);
    }

    /*
        This method should be called after barista manually in their screens click the button that order has started
        That is why for demonstration purposes, I used a simulated timer BaristaTimer
    */
    public void orderStarted(UUID orderId, LocalDateTime startedAt, LocalDateTime readyBy) {
        Order order = Order.findById(orderId);
        order.start(readyBy);
        commandService.orderStarted(orderId, startedAt, readyBy);
    }

    public void finishOrder(UUID orderId) {
        Order order = Order.findById(orderId);
        order.finish();
        commandService.orderFinished(orderId);
    }

    public List<Order> getPendingOrders() {
        return Order.find("select o from Order o where o.readyBy IS NULL").list();
    }

    public List<Order> getUnfinishedOrders() {
        return Order.find("select o from Order o where o.readyBy IS NOT NULL and o.finishedAt IS NULL").list();
    }
}
