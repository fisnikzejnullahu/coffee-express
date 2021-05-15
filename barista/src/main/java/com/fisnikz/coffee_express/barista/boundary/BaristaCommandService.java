package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.entity.MenuItem;
import com.fisnikz.coffee_express.barista.entity.Order;
import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.*;

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

    public void orderAccepted(Order order, LocalDateTime acceptedAt) {
        this.eventProducer.publish(new OrderAccepted(order.id, acceptedAt));
    }

    public void orderStarted(UUID orderId, LocalDateTime startedAt, LocalDateTime readyBy) {
        eventProducer.publish(new OrderStarted(orderId, startedAt, readyBy));
    }

    public void orderFinished(UUID orderId) {
        eventProducer.publish(new OrderFinished(orderId));
    }

    public void menuItemAdded(MenuItem menuItem) {
        eventProducer.publish(new MenuItemAdded(menuItem));
    }

    public void menuItemRemoved(long id) {
        eventProducer.publish(new MenuItemRemoved(id));
    }

    public void menuItemsAdded(List<MenuItem> menuItems) {
        eventProducer.publish(new MenuItemsAdded(menuItems));
    }
}
