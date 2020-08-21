package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.PaymentInformation;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class FinanceService {

    public void authorize(UUID orderId, UUID customerId, PaymentInformation paymentInformation) {
        //TODO: call Stripe api and charge card
        //for now just simulate a wait and return a success

    }
}
