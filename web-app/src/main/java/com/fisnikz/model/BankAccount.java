package com.fisnikz.model;

import javax.persistence.Embedded;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
public class BankAccount {

    private UUID id;
    private UUID customerId;
    private CreditCardInfo creditCardInfo;

    public BankAccount() {
    }

    public BankAccount(UUID customerId, CreditCardInfo creditCardInfo) {
        this.customerId = customerId;
        this.creditCardInfo = creditCardInfo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public CreditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", creditCardInfo=" + creditCardInfo +
                '}';
    }
}
