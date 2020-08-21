package com.fisnikz.coffee_express.barista.control;

import com.fisnikz.coffee_express.barista.entity.OrderItem;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class BaristaService {

    public void prepareCoffee(UUID orderId, List<OrderItem> orderItems) {
    }
}
