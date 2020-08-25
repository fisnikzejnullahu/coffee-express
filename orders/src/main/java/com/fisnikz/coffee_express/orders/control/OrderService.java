package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.boundary.OrderCommandService;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Consumer;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    Logger LOG;

    @Inject
    OrderCommandService commandService;

    public void place(Order order){
        LOG.log(Logger.Level.INFO, "Placing order: " + order.id);
        LOG.log(Logger.Level.INFO, "ORDER: " + JsonbBuilder.create().toJson(order));
        order.place();
        order.persist();
        commandService.placeOrder(order);
    }

    public void customerVerified(UUID orderId) {
        Order order = Order.findById(orderId);
        commandService.authorizeCard(order);
    }

    public void cardAuthorized(UUID orderId) {
        Order order = Order.findById(orderId);
        applyToOrder(orderId, Order::accept);
        commandService.acceptOrder(order);
    }

    public void startOrder(UUID orderId, LocalDateTime readyBy) {
        Order order = Order.findById(orderId);
        order.start(readyBy);
    }

    public void cancelOrder(UUID orderId, String message) {
        LOG.log(Logger.Level.INFO, "Cancelling order: " + orderId);
        applyToOrder(orderId, Order::cancel);
    }

    public void applyToOrder(UUID orderId, Consumer<Order> consumer){
        Order order = Order.findById(orderId);
        if (order != null) {
            consumer.accept(order);
        }
    }
}
