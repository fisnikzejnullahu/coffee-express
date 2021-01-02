package com.fisnikz.coffee_express.orderhistory;

import com.fisnikz.coffee_express.orderhistory.entity.Order;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

@RequestScoped
public class Initializer {

    void onStart(@Observes StartupEvent event) {
        Order.deleteAll();
    }
}
