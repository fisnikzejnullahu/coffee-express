package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.events.FailMessages;
import com.fisnikz.coffee_express.finance.boundary.FinanceCommandService;
import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.finance.entity.Payment;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class FinanceService {

    @Inject
    FinanceCommandService commandService;

    @Counted
    public void authorize(UUID orderId, UUID bankAccountId, UUID customerId, double amount) {
        //TODO: call Stripe api and charge card
        //TODO: validate that the bankAccounts belongs to the customer that want to make payment, not everyone can use bankAccountId to make payments
        //for now just simulate a wait and return a success
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        BankAccount bankAccount = BankAccount.findById(bankAccountId);
        if (bankAccount == null) {
            commandService.cardVerificationFailed(orderId, FailMessages.BANK_ACCOUNT_NOT_FOUND);
        } else {
            if (!bankAccount.customerId.equals(customerId)) {
                commandService.cardVerificationFailed(orderId, FailMessages.BANK_ACCOUNT_NOT_BELONGS_TO_CUSTOMER);
            }else {
                savePayment(orderId, bankAccount, amount);
                commandService.cardVerified(orderId);
            }
        }

    }

    void savePayment(UUID orderId, BankAccount bankAccount, double amount) {
        Payment payment = new Payment();
        payment.id = UUID.randomUUID();
        payment.orderId = orderId;
        payment.account = bankAccount;
        payment.amount = amount;
        payment.timestamp = LocalDateTime.now();

        payment.persist();
    }


}
