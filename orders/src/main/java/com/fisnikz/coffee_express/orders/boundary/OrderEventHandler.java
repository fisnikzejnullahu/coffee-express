package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.control.EventConsumer;
import com.fisnikz.coffee_express.events.entity.DomainEvent;
import com.fisnikz.coffee_express.orders.control.OrderService;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class OrderEventHandler {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Properties kafkaProperties;
    @Inject
    Event<DomainEvent> events;
    @Inject
    OrderService orderService;
    private EventConsumer eventConsumer;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(eventConsumer);
    }

    @PostConstruct
    public void init() {
        String customersTopic = kafkaProperties.getProperty("customers.topic");
        eventConsumer = new EventConsumer(kafkaProperties, event -> {
            events.fire(event);
        }, customersTopic);
    }
}