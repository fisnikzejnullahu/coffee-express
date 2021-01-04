package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.InvalidMenuItemException;
import com.fisnikz.coffee_express.orders.entity.*;
import com.fisnikz.coffee_express.orders.boundary.OrderCommandService;
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
import java.util.stream.Collectors;

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
    public void place(UUID orderId, UUID customerId, PlaceOrderRequest placeOrderRequest) {
        Order order = new Order(orderId, customerId, placeOrderRequest.getBankAccountId(), new OrderDetails(makeOrderItems(placeOrderRequest.getItems())));
        LOG.log(Logger.Level.INFO, "Placing order: " + JsonbBuilder.create().toJson(placeOrderRequest));
        order.place();
        order.persist();
        event.fire(order);
    }

    private List<OrderItem> makeOrderItems(List<PlaceOrderRequest._OrderItem> items) {
        List<MenuItem> menuItems = MenuItem.listAll();
        return items.stream().map(it -> convertItem(it, menuItems)).collect(Collectors.toList());
    }

    private OrderItem convertItem(PlaceOrderRequest._OrderItem item, List<MenuItem> menuItems){
        MenuItem menuItem = menuItems.stream().filter(it -> it.id == item.getMenuItemId() && !it.removed).findFirst()
                .orElseThrow(() -> new InvalidMenuItemException("Menuitem with id: " + item.getMenuItemId() + ", does not exists!"));

        return new OrderItem(menuItem, item.getQuantity());
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
        commandService.acceptOrder(order);
    }

    @Counted(name = "accepted_orders")
    public void orderAccepted(UUID orderId) {
        updateOrder(orderId, Order::accept);
    }

    public void orderStarted(UUID orderId, LocalDateTime readyBy) {
        updateOrder(orderId, Order::start);
        //notify user with that order has satrted with a readyby time
    }

    public void orderFinished(UUID orderId) {
        updateOrder(orderId, Order::finish);
    }

    @Counted(name = "rejected_orders")
    public void rejectOrder(UUID orderId, String reason){
        Order order = Order.findById(orderId);
        order.cancel(reason);
    }

    @Counted(name = "cancelled_orders")
    public void cancelOrder(UUID orderId, String reason) {
        Order order = Order.findById(orderId);
        switch (order.orderState) {
            case PLACED:
                order.cancel(reason);
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

    public void addMenuItem(MenuItem item) {
        item.getEntityManager().merge(item);
    }

    public void addMenuItems(List<MenuItem> items) {
        MenuItem.persist(items);
//        items.forEach(this::addMenuItem);
    }

    public void removeMenuItem(long menuItemId) {
        MenuItem.update("removed = 1 where id = ?1", menuItemId);
    }

}
