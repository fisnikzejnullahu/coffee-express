package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.customers.entity.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class CustomerService {

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

}
