package com.fisnikz.coffee_express.events.control;

import com.fisnikz.coffee_express.events.entity.Command;
import com.fisnikz.coffee_express.events.entity.OrderCommand;
import com.fisnikz.coffee_express.events.entity.OrderEvent;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.*;
import java.lang.System.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class EventConsumer implements Runnable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Event<com.fisnikz.coffee_express.events.entity.Event> events;

    @Inject
    Event<Command> commands;

    @Inject
    ConnectionFactory connectionFactory;

    @Inject
    @ConfigProperty(name = "customers.queue")
    String customersQueue;

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
            JMSConsumer consumer = context.createConsumer(context.createQueue(customersQueue));
            while (true) {
                Message message = consumer.receive();
                if (message == null) {
                    return;
                }
                Object event = serializer.deserialize(message.getBody(String.class));
                LOG.log(Logger.Level.INFO, "CONSUMING: " + event.getClass().getName());
                if (event instanceof com.fisnikz.coffee_express.events.entity.Event) {
                    events.fire((com.fisnikz.coffee_express.events.entity.Event) event);
                }
                else if (event instanceof Command) {
                    commands.fire((Command) event);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
