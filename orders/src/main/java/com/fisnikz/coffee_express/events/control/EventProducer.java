package com.fisnikz.coffee_express.events.control;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;
import javax.transaction.Transactional;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

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
    @ConfigProperty(name = "orders.queue")
    String ordersQueue;

    public void publish(OrderEvent event) {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            context.createProducer().send(context.createQueue(ordersQueue), serializer.serialize(event));
        }
    }
}
