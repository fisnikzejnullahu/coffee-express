package com.fisnikz.coffee_express.customers.boundary;

import com.fisnikz.coffee_express.customers.control.CustomerService;
import com.fisnikz.coffee_express.events.control.EventConsumer;
import com.fisnikz.coffee_express.events.entity.OrderEvent;
import com.fisnikz.coffee_express.events.entity.OrderPlaced;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.lang.System.Logger;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class CustomerEventHandler {

    @Inject
    Logger LOG;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Properties kafkaProperties;
    @Inject
    Event<OrderEvent> events;
    private EventConsumer eventConsumer;

    @Inject
    CustomerService customerService;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(eventConsumer);
    }

    void handleEvent(@Observes OrderPlaced event){
        customerService.verifyCustomer(event.customerId, event.orderId);
    }

    @PostConstruct
    public void init() {
        String ordersTopic = kafkaProperties.getProperty("orders.topic");
        eventConsumer = new EventConsumer(kafkaProperties, event -> {
            LOG.log(Logger.Level.INFO, "CONSUMING: " + event.getClass().getName() + ", data: " + JsonbBuilder.create().toJson(event));
            events.fire(event);
        }, ordersTopic);
    }
}