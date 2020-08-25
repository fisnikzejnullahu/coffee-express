package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.CardAuthorizationFailed;
import com.fisnikz.coffee_express.events.entity.CardAuthorized;
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

    public void cardVerified(UUID orderId) {
        eventProducer.publish(new CardAuthorized(orderId));
    }

    public void cardVerificationFailed(UUID orderId, String message) {
        eventProducer.publish(new CardAuthorizationFailed(orderId, message));
    }
}
