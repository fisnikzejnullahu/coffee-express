package com.fisnikz.coffee_express.customers.entity;

import javax.validation.constraints.Min;

/**
 * @author Fisnik Zejnullahu
 */
public class CreateCustomerRequest {

    private String firstName;
    private String lastName;
    private String username;

    //example for validation
    @Min(value = 6, message = "Min length for password is 6 characters")
    private String password;

    public CreateCustomerRequest() {
    }

    public CreateCustomerRequest(String firstName, String lastName, String username, @Min(value = 6, message = "Min length for password is 6 characters") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
