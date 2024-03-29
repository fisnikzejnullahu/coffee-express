package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.customers.entity.Customer;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RequestScoped
public class Initializer {

    @Inject
    @ConfigProperty(name = "INIT_USER_CUSTOMER_ID")
    String customerId;

    @Transactional
    void onStart(@Observes StartupEvent event) {
        Customer customer = new Customer();
        customer.id = UUID.fromString(customerId);
        customer.firstName = "Fisnik";
        customer.lastName = "Zejnullahu";
        customer.username = "fisnikz";
        customer.registeredAt = LocalDateTime.now();

        customer.persist();
    }
}
