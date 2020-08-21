package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.OrderPlaced;
import com.fisnikz.coffee_express.orders.entity.Order;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class OrderCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    Properties kafkaProperties;

    public void placeOrder(Order order) {
        eventProducer.publish(new OrderPlaced(order.id, order.customerId));
    }
}
