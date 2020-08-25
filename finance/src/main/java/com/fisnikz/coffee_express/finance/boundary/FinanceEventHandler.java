package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.control.EventConsumer;
import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.events.entity.OrderEvent;
import com.fisnikz.coffee_express.finance.control.FinanceService;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.json.bind.JsonbBuilder;
import java.lang.System.Logger;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ApplicationScoped
public class FinanceEventHandler {

    @Inject
    Logger LOG;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Inject
    Properties kafkaProperties;
    @Inject
    Event<OrderEvent> events;
    private EventConsumer eventConsumer;

    @Inject
    FinanceService financeService;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(eventConsumer);
    }

    void handleEvent(@Observes AuthorizeCard event){
        financeService.authorize(event.orderId, event.customerId, event.amount,event.paymentInformation);
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