package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.events.entity.OrderPlaced;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerEventHandler {

    @Inject
    CustomerService customerService;

    void handleEvent(@Observes OrderPlaced event) {
        customerService.verifyCustomer(event.customerId, event.orderId);
    }
}