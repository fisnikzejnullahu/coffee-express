package com.fisnikz.controller;

import com.fisnikz.model.BankAccount;
import com.fisnikz.model.BankAccountService;
import com.fisnikz.model.CreditCardInfo;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

import static com.fisnikz.CustomerInitializer.CUSTOMER_ID;

/**
 * @author Fisnik Zejnullahu
 */
@Controller
@Path("accounts")
public class AccountsController {

    @Inject
    Models models;

    @Inject
    BankAccountService bankAccountService;

    @GET
    @View("accounts.jsp")
    public void home() {
        models.put("current", "account");
        List<BankAccount> bankAccounts = bankAccountService.accountsOfCustomer(CUSTOMER_ID);
        System.out.println(bankAccounts);
        models.put("accounts", bankAccounts);
    }

    @POST
    public Response create(JsonObject creditCard){
        CreditCardInfo creditCardInfo = JsonbBuilder.create().fromJson(creditCard.toString(), CreditCardInfo.class);
        return bankAccountService.create(CUSTOMER_ID, creditCardInfo);
    }

    //added delete/ path, some exception happening if empty path...
    @DELETE
    @Path("delete/{accountId}")
    public Response deleteAccount(@PathParam("accountId") UUID accountId) {
        System.out.println("accountId = " + accountId);
        return Response.ok("hahahahaha").build();
//        return bankAccountService.delete(accountId);
    }

}
