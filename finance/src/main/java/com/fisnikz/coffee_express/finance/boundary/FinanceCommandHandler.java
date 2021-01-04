package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.finance.control.FinanceService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.lang.System.Logger;

@ApplicationScoped
public class FinanceCommandHandler {

    @Inject
    Logger LOG;

    @Inject
    FinanceService financeService;

    void handleEvent(@Observes AuthorizeCard event) {
        financeService.authorize(event.getOrderId(), event.getBankAccountId(), event.getCustomerId(), event.getAmount());
    }
}