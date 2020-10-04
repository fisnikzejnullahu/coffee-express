package com.fisnikz.coffee_express.orderhistory.boundary;

import com.fisnikz.coffee_express.events.entity.*;
import com.fisnikz.coffee_express.orderhistory.control.OrderService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class OrderEventHandler {

    @Inject
    OrderService orderService;

    void handleEvent(@Observes OrderCreated event) {
        orderService.orderCreated(event.orderId, event.orderDetails, event.bankAccountId, event.customerId);
    }

    void handleEvent(@Observes OrderAccepted event){
        orderService.orderAccepted(event.orderId, event.acceptedAt);
    }

    void handleEvent(@Observes CustomerVerificationFailed event) {
        orderService.rejectOrder(event.orderId, event.message);
    }

    void handleEvent(@Observes CardAuthorized event) {
        orderService.paymentDone(event.orderId);
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