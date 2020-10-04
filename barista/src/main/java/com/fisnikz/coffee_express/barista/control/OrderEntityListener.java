package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.boundary.BaristaCommandService;
import com.fisnikz.coffee_express.barista.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;
import java.lang.System.Logger;
import java.time.LocalDateTime;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class OrderEntityListener {

    @Inject
    Logger LOG;

    @Inject
    BaristaCommandService commandService;

    public void onSuccess(@Observes(during= TransactionPhase.AFTER_SUCCESS) Order order){
        LOG.log(Logger.Level.INFO, "After success: ");
        commandService.orderAccepted(order, LocalDateTime.now());
    }
}
