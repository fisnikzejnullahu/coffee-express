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

    void handleEvent(@Observes OrderPlaced event) {
        orderService.orderPlaced(event.getOrderId(), event.getOrderDetails(), event.getBankAccountId(), event.getCustomerId());
    }

    void handleEvent(@Observes OrderAccepted event){
        orderService.orderAccepted(event.getOrderId(), event.acceptedAt);
    }

    void handleEvent(@Observes CustomerVerificationFailed event) {
        orderService.rejectOrder(event.getOrderId(), event.message);
    }

    void handleEvent(@Observes CardAuthorized event) {
        orderService.paymentDone(event.getOrderId());
    }

    void handleEvent(@Observes CardAuthorizationFailed event) {
        orderService.rejectOrder(event.getOrderId(), event.message);
    }

    void handleEvent(@Observes OrderStarted event) {
        orderService.orderStarted(event.getOrderId(), event.readyBy);
    }

    void handleEvent(@Observes OrderFinished event) {
        orderService.orderFinished(event.getOrderId());
    }
}