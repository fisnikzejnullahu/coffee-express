package com.fisnikz.coffee_express.orders.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class PaymentInformation {

    public long cardNumber;
    public LocalDate expirationDate;
    public short cvc;
}