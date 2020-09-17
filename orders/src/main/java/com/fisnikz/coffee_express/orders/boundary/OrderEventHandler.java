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
        orderService.customerVerified(event.orderId);
    }

    void handleEvent(@Observes CustomerVerificationFailed event) {
        orderService.rejectOrder(event.orderId, event.message);
        //TODO: Notify user from a notification-service that his user has cancelled
    }

    void handleEvent(@Observes CardAuthorized event) {
        orderService.cardAuthorized(event.orderId);
    }

    void handleEvent(@Observes CardAuthorizationFailed event) {
        orderService.rejectOrder(event.orderId, event.message);
    }

    void handleEvent(@Observes OrderStarted event) {
        orderService.orderStarted(event.orderId, event.readyBy);
    }

    void handleEvent(@Observes OrderFinished event) {
        orderService.orderFinished(event.orderId);
    }
}