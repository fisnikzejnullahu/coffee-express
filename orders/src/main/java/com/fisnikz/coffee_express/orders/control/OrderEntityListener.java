package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.boundary.OrderCommandService;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class OrderEntityListener {

    @Inject
    Logger LOG;

    @Inject
    OrderCommandService commandService;

    public void onInProgress(@Observes(during = TransactionPhase.IN_PROGRESS) Order order){
        LOG.log(Logger.Level.INFO, "In progress: ");
    }

    public void onSuccess(@Observes(during= TransactionPhase.AFTER_SUCCESS) Order order){
        LOG.log(Logger.Level.INFO, "After success: ");

        commandService.placeOrder(order);
    }

    public void onCompletion(@Observes(during = TransactionPhase.AFTER_COMPLETION) Order order){
        LOG.log(Logger.Level.INFO, "Afte completion: ");
    }

    public void onFailure(@Observes(during= TransactionPhase.AFTER_FAILURE) Order order){
        LOG.log(Logger.Level.INFO, "After failure: " + order.id);
    }
}
