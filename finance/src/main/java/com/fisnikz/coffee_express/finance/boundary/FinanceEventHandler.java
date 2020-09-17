package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.finance.control.FinanceService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class FinanceEventHandler {

    @Inject
    FinanceService financeService;

    void handleEvent(@Observes AuthorizeCard event) {
        financeService.authorize(event.orderId, event.customerId, event.amount, event.paymentInformation);
    }
}