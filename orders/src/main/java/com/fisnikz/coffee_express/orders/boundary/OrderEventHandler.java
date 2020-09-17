package com.fisnikz.coffee_express.orders.boundary;

import com.fisnikz.coffee_express.events.entity.*;
import com.fisnikz.coffee_express.orders.control.OrderService;
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

//@ApplicationScoped
public class OrderEventHandler {

//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    @Inject
//    Properties kafkaProperties;
//    @Inject
//    Event<OrderEvent> events;
//    @Inject
//    OrderService orderService;
//    private EventConsumer eventConsumer;
//
//    @Inject
//    Logger LOG;
//
//    void onStart(@Observes StartupEvent ev) {
//        executorService.submit(eventConsumer);
//    }
//
//    void handleEvent(@Observes CustomerVerified event){
//        orderService.customerVerified(event.orderId);
//    }
//
//    void handleEvent(@Observes CustomerVerificationFailed event){
//        orderService.rejectOrder(event.orderId, event.message);
//        //TODO: Notify user from a notification-service that his user has cancelled
//    }
//
//    void handleEvent(@Observes CardAuthorized event){
//        orderService.cardAuthorized(event.orderId);
//    }
//
//    void handleEvent(@Observes CardAuthorizationFailed event){
//        orderService.rejectOrder(event.orderId, event.message);
//    }
//
//    void handleEvent(@Observes OrderStarted event){
//        orderService.orderStarted(event.orderId, event.readyBy);
//    }
//
//    void handleEvent(@Observes OrderFinished event){
//        orderService.orderFinished(event.orderId);
//    }
//
//    @PostConstruct
//    public void init() {
//        String customersTopic = kafkaProperties.getProperty("customers.topic");
//        String financesTopic = kafkaProperties.getProperty("finances.topic");
//        String baristasTopic = kafkaProperties.getProperty("baristas.topic");
//        eventConsumer = new EventConsumer(kafkaProperties, event -> {
//            LOG.log(Logger.Level.INFO, "CONSUMING: " + event.getClass().getName() + ", data: " + JsonbBuilder.create().toJson(event));
//            events.fire(event);
//        }, customersTopic, financesTopic, baristasTopic);
//    }
}