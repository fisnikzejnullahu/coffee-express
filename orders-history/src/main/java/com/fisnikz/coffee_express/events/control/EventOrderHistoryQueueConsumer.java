package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.OrderEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.lang.System.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventOrderHistoryQueueConsumer implements Runnable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Event<com.fisnikz.coffee_express.events.entity.Event> events;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    @ConfigProperty(name = "orderhistory.queue")
    String ordersHistoryQueue;

    @Inject
    OrderEventJsonbSerializer serializer;

    @Inject
    Logger LOG;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(this);
    }

    @Override
    public void run() {
        try (JMSContext context = connectionFactory.createContext(Session.AUTO_ACKNOWLEDGE)) {
            JMSConsumer consumer = context.createConsumer(context.createQueue(ordersHistoryQueue));
            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    return;
                }
                com.fisnikz.coffee_express.events.entity.Event event = (com.fisnikz.coffee_express.events.entity.Event) serializer.deserialize(message.getBody(String.class));
                LOG.log(Logger.Level.INFO, "CONSUMING: " + event.getClass().getName() + ", DATA: " + JsonbBuilder.create().toJson(event));
                events.fire(event);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
