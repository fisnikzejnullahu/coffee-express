package com.fisnikz.coffee_express.finance.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * @author Fisnik Zejnullahu
 */
@Embeddable
public class PaymentInformation {

    public long cardNumber;
    public LocalDate expirationDate;
    public short cvc;
}
