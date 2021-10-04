package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.CustomerVerificationFailed;
import com.fisnikz.coffee_express.events.entity.CustomerVerified;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class CustomerCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    @ConfigProperty(name = "orders.queue")
    String ordersQueue;

    @Inject
    @ConfigProperty(name = "orders.history.queue")
    String ordersHistoryQueue;

    public void customerVerified(UUID customerId, UUID orderId) {
        eventProducer.publish(new CustomerVerified(customerId, orderId), ordersQueue);
    }

    public void customerVerificationFailed(UUID customerId, UUID orderId, String message) {
        CustomerVerificationFailed event = new CustomerVerificationFailed(customerId, orderId, message);
        eventProducer.publish(event, ordersQueue);
        eventProducer.publish(event, ordersHistoryQueue);
    }
}
