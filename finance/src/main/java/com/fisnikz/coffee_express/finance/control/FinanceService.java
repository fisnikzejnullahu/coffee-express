package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.events.FailMessages;
import com.fisnikz.coffee_express.finance.boundary.FinanceCommandService;
import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.finance.entity.Payment;
import io.micrometer.core.annotation.Counted;

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
        /*
            In production call Stripe Api and charge card
            for now just simulate a wait and return a success
        */
//        commandService.cardVerificationFailed(orderId, FailMessages.CARD_INSUFFICIENT_FUNDS);

        BankAccount bankAccount = BankAccount.findById(bankAccountId);
        if (bankAccount == null) {
            commandService.cardVerificationFailed(orderId, FailMessages.BANK_ACCOUNT_NOT_FOUND);
        } else {
            if (!bankAccount.customerId.equals(customerId)) {
                commandService.cardVerificationFailed(orderId, FailMessages.BANK_ACCOUNT_NOT_BELONGS_TO_CUSTOMER);
            } else {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
