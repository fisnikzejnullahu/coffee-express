package com.fisnikz.coffee_express.orderhistory.control;

import com.fisnikz.coffee_express.orderhistory.entity.Order;
import com.fisnikz.coffee_express.orderhistory.entity.OrderDetails;
import io.quarkus.mongodb.panache.PanacheMongoEntityBase;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class OrderService {

    public void orderCreated(UUID orderId, OrderDetails orderDetails, UUID bankAccountId, UUID customerId) {
        Order order = new Order();
        order.setPlacedAt(LocalDateTime.now());
        order.setOrderState(Order.OrderState.PLACED);
        order.setOrderId(orderId.toString());
        order.setCustomerId(customerId.toString());
        order.setBankAccountId(bankAccountId.toString());
        order.setOrderDetails(orderDetails);
        order.persist();
    }

    public void orderAccepted(UUID orderId, LocalDateTime acceptedAt) {
        Order order = findByOrderId(orderId);
        order.setAcceptedAt(acceptedAt);
        order.setOrderState(Order.OrderState.ACCEPTED);
        order.update();
    }

    public void orderStarted(UUID orderId, LocalDateTime readyBy) {
        Order order = findByOrderId(orderId);
        order.setReadyBy(readyBy);
        order.setOrderState(Order.OrderState.PREPARING);
        order.update();
    }

    public void orderFinished(UUID orderId) {
        Order order = findByOrderId(orderId);
        order.setFinishedAt(LocalDateTime.now());
        order.setOrderState(Order.OrderState.READY_FOR_PICKUP);
        order.update();
    }

    public void rejectOrder(UUID orderId, String message) {
        Order order = findByOrderId(orderId);
        order.setCancelledAt(LocalDateTime.now());
        order.setOrderState(Order.OrderState.CANCELLED);
        order.setCancelledReason(message);
        order.update();
    }

    public void paymentDone(UUID orderId) {
        Order order = findByOrderId(orderId);
        order.setPaid(true);
        order.update();
    }

    private Order findByOrderId(UUID orderId) {
        return Order.find("orderId", orderId.toString()).firstResult();
    }
}
