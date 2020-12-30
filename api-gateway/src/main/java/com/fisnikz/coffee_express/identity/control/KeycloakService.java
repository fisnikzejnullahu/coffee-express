package com.fisnikz.coffee_express.identity.control;

import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import com.fisnikz.coffee_express.identity.entity.Token;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.fisnikz.coffee_express.identity.control.IdentityService.*;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class KeycloakService {

    @Inject
    @RestClient
    KeycloakRestClient keycloakRestClient;

    @Inject
    @ConfigProperty(name = "keycloak.client.id", defaultValue = "coffee-express-admin-api-client")
    String keycloakClientId;

    private Token adminToken;

    @PostConstruct
    public void init() {
        getAdminToken();
    }

    public Object[] login(String username, String password) {
        return loginAndGetUserTokensAndCustomerId(username, password);
    }

    private Object[] loginAndGetUserTokensAndCustomerId(String username, String password) {
        Response loginResponse = keycloakRestClient.login(username, password, "password", keycloakClientId, null);
        if (loginResponse.getStatus() != 200) {
            throw new WebApplicationException(loginResponse);
        }

        JsonObject tokenData = loginResponse.readEntity(JsonObject.class);

        Response userResponse = keycloakRestClient.findUser(toBearerToken(adminToken.getTokenString()), username);
        if (userResponse.getStatus() == 401) {
            getAdminToken();
            keycloakRestClient.findUser(toBearerToken(adminToken.getTokenString()), username);
        }

        if (userResponse.getStatus() != 200) {
            throw new WebApplicationException(userResponse);
        }

        String customerId = null;

        JsonArray usersJson = userResponse.readEntity(JsonArray.class);

        for (JsonValue u : usersJson) {
            JsonObject object = u.asJsonObject();
            if (object.getString("username").equals(username)) {
                customerId = object.getJsonObject("attributes").getJsonArray("customerId").get(0).toString().replace("\"", "");
                break;
            }
        }

        return new Object[]{
                new Token(tokenData.getString("access_token"), Token.TokenType.ACCESS_TOKEN, tokenData.getJsonNumber("expires_in").intValue()),
                new Token(tokenData.getString("refresh_token"), Token.TokenType.REFRESH_TOKEN, tokenData.getJsonNumber("refresh_expires_in").intValue()),
                customerId
        };
    }

    public Token[] refreshToken(String token) {
        Response response = keycloakRestClient.login(null, null, "refresh_token", keycloakClientId, token);

        if (response.getStatus() != 200) {
            throw new WebApplicationException(response);
        }
        JsonObject tokenData = response.readEntity(JsonObject.class);

        return new Token[]{
                new Token(tokenData.getString("access_token"), Token.TokenType.ACCESS_TOKEN, tokenData.getJsonNumber("expires_in").intValue()),
                new Token(tokenData.getString("refresh_token"), Token.TokenType.REFRESH_TOKEN, tokenData.getJsonNumber("refresh_expires_in").intValue())
        };

    }

    private void getAdminToken() {
        JsonObject data = keycloakRestClient.login("fisnikz", "123456", "password", keycloakClientId, null).readEntity(JsonObject.class);
        this.adminToken = new Token(data.getString("access_token"), Token.TokenType.ACCESS_TOKEN, data.getJsonNumber("expires_in").longValue());
    }


    public Response logout(String refreshToken) {
        return keycloakRestClient.logout(refreshToken, keycloakClientId);
    }

    public Response createAccount(CreateCustomerRequest createCustomerRequest, String customerId) {
        JsonObjectBuilder customerIdAttribute = Json.createObjectBuilder()
                .add("customerId", Json.createArrayBuilder().add(customerId));

        JsonObject createAccountBody = Json.createObjectBuilder()
                .add("firstName", createCustomerRequest.getFirstName())
                .add("lastName", createCustomerRequest.getFirstName())
                .add("username", createCustomerRequest.getUsername())
                .add("password", createCustomerRequest.getPassword())
                .add("emailVerified", true)
                .add("enabled", true)
                .add("groups", Json.createArrayBuilder().add("USERS"))
                .add("realmRoles", Json.createArrayBuilder().add("manage_orders"))
                .add("attributes", customerIdAttribute)
                .build();

        return keycloakRestClient.create(createAccountBody);
    }
}
