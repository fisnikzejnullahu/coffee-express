package com.fisnikz.coffee_express.identity.control;

import com.fisnikz.coffee_express.customers.control.CustomersRestClient;
import com.fisnikz.coffee_express.customers.entity.UpdateCustomerRequest;
import com.fisnikz.coffee_express.identity.entity.LoginInfo;
import com.fisnikz.coffee_express.identity.entity.Token;
import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import com.fisnikz.coffee_express.logging.Logged;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Logged
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
        String accountId = (String) loginData[2];
        String customerId = (String) loginData[3];

        Response response = customersRestClient.find(toBearerToken(accessToken.getTokenString()), customerId);

        if (response.getStatus() != 200) {
            return response;
        }

        JsonObject body = Json.createObjectBuilder(response.readEntity(JsonObject.class))
                .add("account_id", accountId)
                .build();

        return Response.ok(body)
                .cookie(accessToken.toCookie())
                .cookie(refreshToken.toCookie())
                .cookie(new NewCookie("signedIn", "1", "/", null, null, refreshToken.getExpiresIn(), false, false))
                .build();
    }

    public Response refreshToken(String refreshToken) {
        Token[] tokens = keycloakService.refreshToken(refreshToken);
        return Response.ok()
                .cookie(tokens[0].toCookie())
                .cookie(tokens[1].toCookie())
                .cookie(new NewCookie("signedIn", "1", "/", null, null, tokens[1].getExpiresIn(), false, false))
                .build();
    }

    public Response logout(String refreshToken) {
        Response logoutResponse = keycloakService.logout(refreshToken);

        if (logoutResponse.getStatus() == 204) {
            return Response.fromResponse(logoutResponse)
                    .cookie(new Token(null, Token.TokenType.ACCESS_TOKEN, 0).toCookie())
                    .cookie(new Token(null, Token.TokenType.REFRESH_TOKEN, 0).toCookie())
                    .cookie(new NewCookie("signedIn", "0", "/", null, null, 0, false, false))
                    .build();
        }

        return logoutResponse;
    }

//    @Fallback(fallbackMethod = "onCreateUserAccountFail")
    public Response createUserAccount(CreateCustomerRequest createCustomerRequest, String customerId) {
        return keycloakService.createAccount(createCustomerRequest, customerId);
    }

    public Response onCreateUserAccountFail(CreateCustomerRequest createCustomerRequest, String customerId) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
    }

    public Response updateUserAccount(String customerId, UpdateCustomerRequest request) {
        return keycloakService.update(customerId, request);
    }

    public String getAdminToken() {
        return toBearerToken(keycloakService.getAdminToken());
    }
}
