package com.fisnikz.coffee_express.barista.boundary;

import com.fisnikz.coffee_express.events.control.EventProducer;
import com.fisnikz.coffee_express.events.entity.OrderFinished;
import com.fisnikz.coffee_express.events.entity.OrderStarted;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class BaristaCommandService {

    @Inject
    EventProducer eventProducer;

    public void orderStarted(UUID orderId, LocalDateTime readyBy) {
        eventProducer.publish(new OrderStarted(orderId, readyBy));
    }

    public void orderFinished(UUID orderId) {
        eventProducer.publish(new OrderFinished(orderId));
    }
}
