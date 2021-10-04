package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.entity.MenuItem;
import com.fisnikz.coffee_express.barista.entity.Order;
import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.*;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class BaristaCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    @ConfigProperty(name = "orders.queue")
    String ordersQueue;

    @Inject
    @ConfigProperty(name = "orders.history.queue")
    String ordersHistoryQueue;

    public void orderAccepted(Order order, LocalDateTime acceptedAt) {
        OrderAccepted event = new OrderAccepted(order.id, acceptedAt);
        this.eventProducer.publish(event, ordersQueue);
        this.eventProducer.publish(event, ordersHistoryQueue);
    }

    public void orderStarted(UUID orderId, LocalDateTime startedAt, LocalDateTime readyBy) {
        OrderStarted event = new OrderStarted(orderId, startedAt, readyBy);
        eventProducer.publish(event, ordersQueue);
        eventProducer.publish(event, ordersHistoryQueue);
    }

    public void orderFinished(UUID orderId) {
        OrderFinished event = new OrderFinished(orderId);
        eventProducer.publish(event, ordersQueue);
        eventProducer.publish(event, ordersHistoryQueue);
    }

    public void menuItemAdded(MenuItem menuItem) {
        eventProducer.publish(new MenuItemAdded(menuItem), ordersQueue);
    }

    public void menuItemRemoved(long id) {
        eventProducer.publish(new MenuItemRemoved(id), ordersQueue);
    }

    public void menuItemsAdded(List<MenuItem> menuItems) {
        eventProducer.publish(new MenuItemsAdded(menuItems), ordersQueue);
    }
}
