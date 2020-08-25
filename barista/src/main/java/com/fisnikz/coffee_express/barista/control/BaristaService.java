package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.boundary.BaristaCommandService;
import com.fisnikz.coffee_express.barista.entity.OrderItem;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class BaristaService {

    @Inject
    BaristaCommandService commandService;

    public void prepareCoffee(UUID orderId, List<OrderItem> orderItems) {
        commandService.coffeeStarted(orderId, LocalDateTime.now().plusMinutes(10));
    }
}
