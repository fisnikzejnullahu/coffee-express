package com.fisnikz.coffee_express.finance.entity;

import javax.persistence.Embeddable;
import java.time.LocalDate;

/**
 * @author Fisnik Zejnullahu
 */
@Embeddable
public class CreditCardInfo {

    public long cardNumber;
    public LocalDate expirationDate;
    public short cvc;
}
