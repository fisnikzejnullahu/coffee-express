package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class BaristaCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    Properties kafkaProperties;

    public void ticketCreated(UUID orderId) {
//        eventProducer.publish(new CustomerVerified(customerId, orderId));
    }

}
