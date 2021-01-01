package com.fisnikz.coffee_express.customers.control;

import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
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
        Response createCustomerResponse = customersRestClient.create(createCustomerRequest);

        if (createCustomerResponse.getStatus() != 201) {
            throw new WebApplicationException(createCustomerResponse);
        }
        String customerId = getCustomerIdFromLocationHeader(createCustomerResponse.getLocation());
        System.out.println("1");
        System.out.println("customerId = " + customerId);
        System.out.println("1");

        Response createAccountResponse = null;
        try {
            createAccountResponse = identityService.createUserAccount(createCustomerRequest, customerId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(createAccountResponse.getLocation());
        System.out.println(createAccountResponse.getStatus());
        if (createAccountResponse.getStatus() != 201) {
            throw new WebApplicationException(createCustomerResponse);
        }

        return customerId;
    }

    private String getCustomerIdFromLocationHeader(URI location) {
        return location.toString().substring(location.toString().lastIndexOf("/") + 1);
    }

    public Response find(String token, String customerId) {
        return customersRestClient.find(token, customerId);
    }
}
