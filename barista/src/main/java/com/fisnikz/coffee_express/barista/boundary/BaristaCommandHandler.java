package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.barista.control.BaristaService;
import com.fisnikz.coffee_express.events.entity.AcceptOrder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class BaristaCommandHandler {

    @Inject
    BaristaService baristaService;

    void handleEvent(@Observes AcceptOrder event){
        baristaService.acceptOrder(event.getOrderId(), event.getOrderItems());
    }
}