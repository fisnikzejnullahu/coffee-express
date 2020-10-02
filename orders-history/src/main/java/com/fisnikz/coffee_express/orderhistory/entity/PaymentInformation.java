package com.fisnikz.coffee_express.orderhistory.entity;

import java.time.LocalDate;

/**
 * @author Fisnik Zejnullahu
 */
public class PaymentInformation {

    public long cardNumber;
    public LocalDate expirationDate;
    public short cvc;
}
