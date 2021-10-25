package com.fisnikz.coffee_express.finance.entity;

import javax.json.bind.annotation.JsonbProperty;
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

    @JsonbProperty
    public Long getCardNumber() {
        return Long.valueOf(String.valueOf(this.cardNumber).substring(12));
    }
}
