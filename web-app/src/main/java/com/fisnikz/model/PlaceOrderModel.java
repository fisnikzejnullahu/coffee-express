package com.fisnikz.model;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Model;
import javax.inject.Named;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RequestScoped
public class PlaceOrderModel {

    private BankAccount bankAccount;

    public PlaceOrderModel() {
    }

    public PlaceOrderModel(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    @Override
    public String toString() {
        return "PlaceOrderModel{" +
                "bankAccount=" + bankAccount +
                '}';
    }
}
