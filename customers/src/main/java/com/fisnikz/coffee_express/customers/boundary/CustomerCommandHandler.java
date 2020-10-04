package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.events.entity.VerifyCustomer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class CustomerCommandHandler {

    @Inject
    CustomerService customerService;

    void handleEvent(@Observes VerifyCustomer event) {
        customerService.verifyCustomer(event.getOrderId(), event.getCustomerId());
    }
}