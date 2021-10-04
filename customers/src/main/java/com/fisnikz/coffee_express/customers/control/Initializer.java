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

    @Transactional
    void onStart(@Observes StartupEvent event) {
        Customer customer = new Customer();
        customer.id = UUID.fromString("045cf19e-34b9-4d1e-a566-921874129ff0");
        customer.firstName = "Fisnik";
        customer.lastName = "Zejnullahu";
        customer.username = "fisnikz";
        customer.registeredAt = LocalDateTime.now();

        customer.persist();
    }
}
