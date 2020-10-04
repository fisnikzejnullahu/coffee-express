package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.entity.*;
import com.fisnikz.coffee_express.orders.control.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class OrderEventHandler {

    @Inject
    OrderService orderService;

    void handleEvent(@Observes CustomerVerified event) {
        orderService.customerVerified(event.getOrderId());
    }

    void handleEvent(@Observes CustomerVerificationFailed event) {
        orderService.rejectOrder(event.getOrderId(), event.getMessage());
        //TODO: Notify user from a notification-service that his user has cancelled
    }

    void handleEvent(@Observes CardAuthorized event) {
        orderService.cardAuthorized(event.getOrderId());
    }

    void handleEvent(@Observes CardAuthorizationFailed event) {
        orderService.rejectOrder(event.getOrderId(), event.getMessage());
    }

    void handleEvent(@Observes OrderAccepted event) {
        orderService.orderAccepted(event.getOrderId());
    }

    void handleEvent(@Observes OrderStarted event) {
        orderService.orderStarted(event.getOrderId(), event.getReadyBy());
    }

    void handleEvent(@Observes OrderFinished event) {
        orderService.orderFinished(event.getOrderId());
    }
}