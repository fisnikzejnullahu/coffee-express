package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.Payment;
import com.fisnikz.coffee_express.finance.boundary.FinanceCommandService;
import com.fisnikz.coffee_express.finance.entity.PaymentInformation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class FinanceService {

    @Inject
    FinanceCommandService commandService;

    public void authorize(UUID orderId, UUID customerId, double amount, PaymentInformation paymentInformation) {
        //TODO: call Stripe api and charge card
        //for now just simulate a wait and return a success
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        savePayment(orderId, customerId, amount, paymentInformation);
        commandService.cardVerified(orderId);
    }

    void savePayment(UUID orderId, UUID customerId, double amount, PaymentInformation paymentInformation) {
        Payment payment = new Payment();
        payment.id = UUID.randomUUID();
        payment.orderId = orderId;
//        payment.customerId = customerId;
        payment.amount = amount;
//        payment.paymentInformation = paymentInformation;

        payment.persist();
    }
}
