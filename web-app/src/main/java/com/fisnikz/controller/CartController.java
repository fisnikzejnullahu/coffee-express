package com.fisnikz.controller;

import com.fisnikz.BankAccountsResource;
import com.fisnikz.BaristasResourceClient;
import com.fisnikz.CustomerInitializer;
import com.fisnikz.model.BankAccount;
import com.fisnikz.model.Cart;
import com.fisnikz.model.MenuItem;
import com.fisnikz.model.PlaceOrderModel;

import javax.inject.Inject;
import javax.json.JsonValue;
import javax.json.bind.JsonbBuilder;
import javax.mvc.Controller;
import javax.mvc.Models;
import javax.mvc.View;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static com.fisnikz.CustomerInitializer.BANK_ACCOUNT_ID;
import static com.fisnikz.CustomerInitializer.CUSTOMER_ID;

/**
 * @author Fisnik Zejnullahu
 */
@Controller
@Path("cart")
public class CartController {

    @Inject
    Cart cart;

    @Inject
    Models models;

    @Inject
    BaristasResourceClient baristasResourceClient;

    @Inject
    BankAccountsResource bankAccountsResource;

    @GET
    @View("cart.jsp")
    public void index() {

    }

    @GET
    @Path("/checkout")
    @View("checkout.jsp")
    public void checkout() {;
        models.put("checkoutInfo", new PlaceOrderModel(bankAccountsResource.popularAccount(CUSTOMER_ID)));
    }

    @POST
    @Path("/addInCart")
    public String addInCart(@FormParam("menuItemId") long menuItemId,
                            @FormParam("quantity") short quantity,
                            @Context HttpHeaders headers) {

        System.out.println("id: " + menuItemId + ", " + "quantity: " + quantity);
        MenuItem item = getItem(menuItemId);
        cart.add(item, quantity);

        String referer = headers.getHeaderString("referer");

        if (referer.endsWith("menu/")) {
            return "redirect:/menu/";
        }
        return "redirect:/menu/details/" + menuItemId;
    }

    @POST
    @Path("/removeFromCart")
    public String removeFromCart(@FormParam("menuItemId") long menuItemId) {
        MenuItem item = getItem(menuItemId);
        cart.remove(menuItemId);
        return "redirect:/menu/details/" + menuItemId;
    }

    private MenuItem getItem(long id) {
        Response itemDetails = baristasResourceClient.getItemDetails(id);
        return toMenuItem(itemDetails.readEntity(JsonValue.class));
    }

    private MenuItem toMenuItem(JsonValue jsonValue) {
        return JsonbBuilder.create().fromJson(jsonValue.toString(), MenuItem.class);
    }
}
