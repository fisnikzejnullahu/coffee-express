package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.boundary.OrderCommandService;
import com.fisnikz.coffee_express.orders.entity.Order;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.util.List;
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

    @Inject
    Event<Order> event;

    @Counted(name = "placed_orders")
    public void place(Order order) {
        LOG.log(Logger.Level.INFO, "Placing order: " + JsonbBuilder.create().toJson(order));
        order.place();
        order.persist();
        event.fire(order);
//        commandService.placeOrder(order);
    }

    public void customerVerified(UUID orderId) {
        Order order = Order.findById(orderId);
        if (order != null) {
            commandService.authorizeCard(order);
        }
        else {
            LOG.log(System.Logger.Level.ERROR, orderId);
        }
    }

    public void cardAuthorized(UUID orderId) {
        Order order = Order.findById(orderId);
        updateOrder(orderId, Order::accept);
        commandService.acceptOrder(order);
    }

    @Counted
    public void orderStarted(UUID orderId, LocalDateTime readyBy) {
        Order order = Order.findById(orderId);
        order.start(readyBy);
    }

    public void orderFinished(UUID orderId) {
        Order order = Order.findById(orderId);
        order.finish();
    }

    @Counted(name = "rejected_orders")
    public void rejectOrder(UUID orderId, String message){
        updateOrder(orderId, Order::cancel);
    }

    @Counted(name = "cancelled_orders")
    public void cancelOrder(UUID orderId) {
        Order order = Order.findById(orderId);
        switch (order.orderState) {
            case PLACED:
                commandService.cancelOrder(orderId);
                break;
            case CANCELLED:
                throw new WebApplicationException(Response.status(400).header("cause", "Order has already been canceled").build());
            default:
                throw new WebApplicationException(Response.status(400).header("cause", "Order has already started and cannot be canceled").build());
        }
    }

    public void updateOrder(UUID orderId, Consumer<Order> consumer) {
        Order order = Order.findById(orderId);
        if (order != null) {
            consumer.accept(order);
        }
    }

    public List<Order> getOrdersOfCustomer(UUID customerId, int page) {
        return Order.find("customerId", Sort.descending("placedAt"), customerId)
                .page(page, 5)
                .list();
    }

    public List<Order> getOrders(int page) {
        return Order.findAll(Sort.descending("placedAt"))
                .page(page, 7)
                .list();
    }
}
