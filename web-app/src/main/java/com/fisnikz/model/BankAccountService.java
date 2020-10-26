package com.fisnikz.model;

import com.fisnikz.BankAccountsResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class BankAccountService {

    @Inject
    BankAccountsResource bankAccountsResource;

    public Response create(UUID customerId, CreditCardInfo creditCardInfo){
        //check for any validation logic, e.g. not empty, credit info correct length etc
        return bankAccountsResource.create(new BankAccount(customerId, creditCardInfo));
    }

    public BankAccount find(UUID bankAccountId) {
        return bankAccountsResource.find(bankAccountId);
    }
    
    public List<BankAccount> accountsOfCustomer(UUID customerId) {
        return bankAccountsResource.accountsOfCustomer(customerId);
    }

//    public Response delete(UUID accountId) {
//        return bankAccountsResource.delete(accountId);
//    }
}
