package com.fisnikz.coffee_express.finance.entity;

import javax.json.bind.annotation.JsonbTransient;
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

    @JsonbTransient
    public long getCardNumber() {
        return this.cardNumber;
    }

    public String getEndingCardNumbers() {
        return String.valueOf(this.cardNumber).substring(12);
    }
}
