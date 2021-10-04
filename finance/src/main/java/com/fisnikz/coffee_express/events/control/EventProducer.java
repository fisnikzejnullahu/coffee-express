package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;

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
    System.Logger LOG;

    public void publish(Object event, String queue) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            LOG.log(System.Logger.Level.INFO, "Publishing event: " + event.getClass().getName());
            context.createProducer().send(context.createQueue(queue), serializer.serialize(event));
        }
    }
}
