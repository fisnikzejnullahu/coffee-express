package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.customers.boundary.CustomerCommandService;
import com.fisnikz.coffee_express.customers.entity.Customer;
import com.fisnikz.coffee_express.events.FailMessages;

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
            commandService.customerVerificationFailed(customerId, orderId, FailMessages.CUSTOMER_NOT_FOUND);
        }
        else if (customer.banned) {
            commandService.customerVerificationFailed(customerId, orderId, FailMessages.CUSTOMER_BANNED);
        }
        else {
            commandService.customerVerified(customerId, orderId);
        }
    }
}
