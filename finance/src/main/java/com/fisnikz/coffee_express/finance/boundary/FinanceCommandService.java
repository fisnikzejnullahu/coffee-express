package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.entity.CardAuthorizationFailed;
import com.fisnikz.coffee_express.events.entity.CardAuthorized;
import com.fisnikz.coffee_express.events.control.EventProducer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class FinanceCommandService {

    @Inject
    EventProducer eventProducer;

    @Inject
    @ConfigProperty(name = "orders.queue")
    String ordersQueue;

    @Inject
    @ConfigProperty(name = "orders.history.queue")
    String ordersHistoryQueue;

    public void cardVerified(UUID orderId) {
        CardAuthorized event = new CardAuthorized(orderId);
        eventProducer.publish(event, ordersQueue);
        eventProducer.publish(event, ordersHistoryQueue);
    }

    public void cardVerificationFailed(UUID orderId, String message) {
        CardAuthorizationFailed event = new CardAuthorizationFailed(orderId, message);
        eventProducer.publish(event, ordersQueue);
        eventProducer.publish(event, ordersHistoryQueue);
    }
}
