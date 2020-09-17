package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventConsumer implements Runnable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Event<OrderEvent> events;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    OrderEventJsonbSerializer serializer;

    @Inject
    @ConfigProperty(name = "orders.topic")
    String ordersTopic;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(this);
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(context.createTopic(ordersTopic));
            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    return;
                }
                events.fire(serializer.deserialize(message.getBody(String.class)));
            }
        } catch (JMSException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
