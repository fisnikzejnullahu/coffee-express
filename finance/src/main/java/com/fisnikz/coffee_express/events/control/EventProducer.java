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
    @ConfigProperty(name = "orders.topic")
    String ordersTopic;

    public void publish(OrderEvent event) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createTopic(ordersTopic), serializer.serialize(event));
        }
    }
}
