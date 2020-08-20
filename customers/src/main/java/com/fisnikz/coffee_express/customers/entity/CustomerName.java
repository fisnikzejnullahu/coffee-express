package com.fisnikz.coffee_express.customers.entity;

import javax.persistence.Embeddable;

/**
 * @author Fisnik Zejnullahu
 */
@Embeddable
public class CustomerName {

    public String firstName;
    public String lastName;

    public CustomerName() {
    }

    public CustomerName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
