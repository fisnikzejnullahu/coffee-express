package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.CustomerVerificationFailed;
import com.fisnikz.coffee_express.events.entity.CustomerVerified;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class CustomerCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    Properties kafkaProperties;

    public void customerVerified(UUID customerId, UUID orderId) {
        eventProducer.publish(new CustomerVerified(customerId, orderId));
    }

    public void customerVerificationFailed(UUID customerId, UUID orderId, String message) {
        eventProducer.publish(new CustomerVerificationFailed(customerId, orderId, message));
    }
}
