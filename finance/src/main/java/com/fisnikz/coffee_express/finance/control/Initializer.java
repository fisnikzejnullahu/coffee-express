package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.finance.entity.CreditCardInfo;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RequestScoped
public class Initializer {

    @Transactional
    void onStart(@Observes StartupEvent event) {
        CreditCardInfo creditCardInfo = new CreditCardInfo();
        creditCardInfo.cardNumber = 378282246310005L;
        creditCardInfo.cvc = 123;
        creditCardInfo.expirationDate = LocalDate.now().plusYears(2);

        BankAccount bankAccount = new BankAccount();
        bankAccount.id = UUID.fromString("70d273a8-03ec-11eb-adc1-0242ac120002");
        bankAccount.customerId = UUID.fromString("045cf19e-34b9-4d1e-a566-921874129ff0");
        bankAccount.creditCardInfo = creditCardInfo;

        bankAccount.persist();
    }
}
