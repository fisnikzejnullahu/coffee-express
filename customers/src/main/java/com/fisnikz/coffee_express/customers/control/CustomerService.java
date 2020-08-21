package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.LoggingSystem;
import com.fisnikz.coffee_express.customers.boundary.CustomerCommandService;
import com.fisnikz.coffee_express.customers.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.lang.System.Logger;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class CustomerService {

    @Inject
    Logger LOG;

    @Inject
    CustomerCommandService commandService;

    public UUID create(Customer customer) {
        customer.id = UUID.randomUUID();
        customer.registeredAt = LocalDateTime.now();
        customer.banned = false;

        customer.persist();
        return customer.id;
    }

    public Customer getCustomer(UUID customerId) {
        return Customer.findById(customerId);
    }

    public void verifyCustomer(UUID customerId, UUID orderId) {
        LOG.log(Logger.Level.INFO, "verifying customer: " + customerId + ", for order: " + orderId);
        Customer customer = Customer.findById(customerId);
        if (customer == null) {
            commandService.customerVerificationFailed(customerId, orderId, "Customer was not found!");
        }
        else if (customer.banned) {
            commandService.customerVerificationFailed(customerId, orderId, "Customer is banned from placing orders!");
        }
        else {
            commandService.customerVerified(customerId, orderId);
        }
    }
}
