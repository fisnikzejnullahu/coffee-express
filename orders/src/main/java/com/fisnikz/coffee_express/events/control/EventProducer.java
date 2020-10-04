package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderCommand;
import com.fisnikz.coffee_express.events.entity.OrderEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventProducer {

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    OrderEventJsonbSerializer serializer;

    @Inject
    Logger LOG;

    public void publish(Object event, String queue) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            String serialize = serializer.serialize(event);
            LOG.log(Logger.Level.INFO, "Publishing: " + serialize);
            context.createProducer().send(context.createQueue(queue), serialize);
        }
    }
}
