package com.fisnikz;

import com.fisnikz.model.BankAccount;
import com.fisnikz.model.CreateOrderRequest;
import com.fisnikz.model.Order;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@RegisterRestClient(baseUri = "http://localhost:8082")
@Path("accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterProvider(LowerCaseWithDashesJsonbNamingStrategy.class)
public interface BankAccountsResource {

    @POST
    Response create(BankAccount account);

    @GET
    @Path("{id}")
    BankAccount find(@PathParam("id") UUID id);

    @GET
    @Path("popular")
    BankAccount popularAccount(@QueryParam("customerId") UUID customerId);
}
