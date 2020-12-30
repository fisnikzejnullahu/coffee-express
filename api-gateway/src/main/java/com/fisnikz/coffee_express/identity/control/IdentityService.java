package com.fisnikz.coffee_express.identity.control;

import com.fisnikz.coffee_express.customers.control.CustomersRestClient;
import com.fisnikz.coffee_express.identity.entity.LoginInfo;
import com.fisnikz.coffee_express.identity.entity.Token;
import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class IdentityService {

    @Inject
    @RestClient
    CustomersRestClient customersRestClient;

    @Inject
    KeycloakService keycloakService;

    public static String toBearerToken(String token) {
        return (token != null) ? "Bearer " + token : "";
    }

    public Response login(LoginInfo loginInfo) {
        Object[] loginData = keycloakService.login(loginInfo.getUsername(), loginInfo.getPassword());
        Token accessToken = (Token) loginData[0];
        Token refreshToken = (Token) loginData[1];
        String customerId = (String) loginData[2];

        Response response = customersRestClient.find(toBearerToken(accessToken.getTokenString()), customerId);

        if (response.getStatus() != 200) {
            return response;
        }

        return Response.ok(response.getEntity())
                .cookie(accessToken.toCookie())
                .cookie(refreshToken.toCookie())
                .build();
    }

    public Response refreshToken(String refreshToken) {
        Token[] tokens = keycloakService.refreshToken(refreshToken);
        return Response.ok()
                .cookie(tokens[0].toCookie())
                .cookie(tokens[1].toCookie())
                .build();
    }

    public Response logout(String refreshToken) {
        Response logoutResponse = keycloakService.logout(refreshToken);

        if (logoutResponse.getStatus() == 204) {
            return Response.fromResponse(logoutResponse)
                    .cookie(new Token(null, Token.TokenType.ACCESS_TOKEN, 0).toCookie())
                    .cookie(new Token(null, Token.TokenType.REFRESH_TOKEN, 0).toCookie())
                    .build();
        }

        return logoutResponse;
    }

    @Fallback(fallbackMethod = "onCreateUserAccountFail")
    public Response createUserAccount(CreateCustomerRequest createCustomerRequest, String customerId) {
        return keycloakService.createAccount(createCustomerRequest, customerId);
    }

    public Response onCreateUserAccountFail(CreateCustomerRequest createCustomerRequest, String customerId) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }
}
