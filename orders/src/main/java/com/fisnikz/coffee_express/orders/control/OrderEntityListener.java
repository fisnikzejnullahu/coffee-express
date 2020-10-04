package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.entity.Order;
import com.fisnikz.coffee_express.orders.boundary.OrderCommandService;

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
    OrderCommandService commandService;

    public void onSuccess(@Observes(during= TransactionPhase.AFTER_SUCCESS) Order order){
        commandService.placeOrder(order);
    }

}
