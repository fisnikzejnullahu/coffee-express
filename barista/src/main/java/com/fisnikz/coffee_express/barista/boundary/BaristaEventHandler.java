package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.control.BaristaService;
import com.fisnikz.coffee_express.events.entity.AcceptOrder;
import com.fisnikz.coffee_express.events.entity.OrderAccepted;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class BaristaEventHandler {

    @Inject
    BaristaService baristaService;

    void handleEvent(@Observes AcceptOrder event){
        baristaService.acceptOrder(event.orderId, event.orderItems);
    }
}