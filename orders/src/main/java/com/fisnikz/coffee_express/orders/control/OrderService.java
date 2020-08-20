package com.fisnikz.coffee_express.orders.control;

import com.fisnikz.coffee_express.orders.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class OrderService {

    @Inject
    Logger LOG;

    public void place(Order order){
        LOG.log(Logger.Level.INFO, "Placing order: " + order.id);
        order.place();
        order.persist();
        LOG.log(Logger.Level.INFO, "Order Persisted: " + order.id);
    }
}
