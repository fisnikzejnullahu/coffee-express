package com.fisnikz.model;

import java.time.LocalDate;

/**
 * @author Fisnik Zejnullahu
 */
public class CreditCardInfo {

    private long cardNumber;
    private LocalDate expirationDate;
    private short cvc;

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public short getCvc() {
        return cvc;
    }

    public void setCvc(short cvc) {
        this.cvc = cvc;
    }

    public String lastDigits() {
        return String.valueOf(this.cardNumber).substring(12);
    }

    @Override
    public String toString() {
        return "CreditCardInfo{" +
                "cardNumber=" + cardNumber +
                ", expirationDate=" + expirationDate +
                ", cvc=" + cvc +
                '}';
    }
}
