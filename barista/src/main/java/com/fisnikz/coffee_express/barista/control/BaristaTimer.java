package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.entity.Order;
import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class BaristaTimer {

    @Inject
    BaristaService baristaService;

    @Scheduled(every="7s")
    void startPreparingCoffes() {
        List<Order> pendingOrders = baristaService.getPendingOrders();
        pendingOrders.forEach(order -> {
            baristaService.orderStarted(order.id, order.acceptedAt.plusMinutes(new Random().nextInt(1) + 1));
        });
    }

    @Scheduled(every="2m")
    void finishCoffes() {
        List<Order> unfinishedOrders = baristaService.getUnfinishedOrders();
        unfinishedOrders.forEach(order -> {
            baristaService.finishOrder(order.id);
        });
    }

}
