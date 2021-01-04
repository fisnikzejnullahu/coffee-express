package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import com.fisnikz.coffee_express.customers.entity.UpdateCustomerRequest;
import com.fisnikz.coffee_express.identity.control.IdentityService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.net.URI;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class CustomersService {

    @Inject
    @RestClient
    CustomersRestClient customersRestClient;

    @Inject
    IdentityService identityService;

    public String create(CreateCustomerRequest createCustomerRequest) {
        Response createCustomerResponse = customersRestClient.create(identityService.getAdminToken(), createCustomerRequest);

        String customerId = getCustomerIdFromLocationHeader(createCustomerResponse.getLocation());
        System.out.println("CREATED: " + customerId);
        try {
            identityService.createUserAccount(createCustomerRequest, customerId);
        }catch (WebApplicationException ex) {
            /*
            if some exception happened
            delete the created customer and then throw the ex with response
         */
            customersRestClient.delete(identityService.getAdminToken(), customerId);
            throw ex;
        }

        return customerId;
    }

    public Response update(String customerId, UpdateCustomerRequest updateCustomerRequest) {
        System.out.println("UPDATING");
        customersRestClient.update(identityService.getAdminToken(), customerId, updateCustomerRequest);
        identityService.updateUserAccount(customerId, updateCustomerRequest);

        return Response.noContent().build();
    }

    private String getCustomerIdFromLocationHeader(URI location) {
        return location.toString().substring(location.toString().lastIndexOf("/") + 1);
    }

    public Response find(String token, String customerId) {
        return customersRestClient.find(token, customerId);
    }
}
