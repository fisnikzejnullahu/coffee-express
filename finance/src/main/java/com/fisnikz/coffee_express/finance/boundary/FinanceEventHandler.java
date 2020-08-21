package com.fisnikz.coffee_express.finance.boundary;

import com.fisnikz.coffee_express.events.control.EventConsumer;
import com.fisnikz.coffee_express.events.entity.AuthorizeCard;
import com.fisnikz.coffee_express.events.entity.DomainEvent;
import com.fisnikz.coffee_express.finance.control.FinanceService;
import io.quarkus.runtime.StartupEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.lang.System.Logger;
import java.util.Properties;
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
    Event<DomainEvent> events;
    private EventConsumer eventConsumer;

    @Inject
    FinanceService financeService;

    void onStart(@Observes StartupEvent ev) {
        executorService.submit(eventConsumer);
    }

    void handleEvent(@Observes AuthorizeCard event){
        financeService.authorize(event.orderId, event.customerId, event.paymentInformation);
    }

    @PostConstruct
    public void init() {
        String ordersTopic = kafkaProperties.getProperty("orders.topic");
        eventConsumer = new EventConsumer(kafkaProperties, event -> {
            events.fire(event);
        }, ordersTopic);
    }
}