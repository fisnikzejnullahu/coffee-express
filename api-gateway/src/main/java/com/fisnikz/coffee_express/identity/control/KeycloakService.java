package com.fisnikz.coffee_express.identity.control;

import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import com.fisnikz.coffee_express.customers.entity.UpdateCustomerRequest;
import com.fisnikz.coffee_express.identity.entity.Token;
import com.fisnikz.coffee_express.logging.Logged;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static com.fisnikz.coffee_express.identity.control.IdentityService.toBearerToken;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Startup
@Logged
public class KeycloakService {

    @Inject
    @RestClient
    KeycloakRestClient keycloakRestClient;

    @Inject
    @ConfigProperty(name = "keycloak.public.realm.client.id", defaultValue = "123coffee-express-admin-api-client")
    String keycloakClientId;

    private Token adminToken;

    public Object[] login(String username, String password) {
        return loginAndGetUserTokensAndCustomerId(username, password);
    }

    private Object[] loginAndGetUserTokensAndCustomerId(String username, String password) {
        try {
            Response loginResponse = keycloakRestClient.login(username, password, "password", keycloakClientId, null);
            JsonObject tokenData = loginResponse.readEntity(JsonObject.class);

            Response userResponse = keycloakRestClient.findUser(toBearerToken(getAdminToken()), username);

            if (userResponse.getStatus() != 200) {
                throw new WebApplicationException(userResponse);
            }

            String customerId = null;
            //keycloak accountId
            String accountId = null;

            JsonArray usersJson = userResponse.readEntity(JsonArray.class);

            for (JsonValue u : usersJson) {
                JsonObject object = u.asJsonObject();
                if (object.getString("username").equals(username)) {
                    accountId = object.getString("id");
                    customerId = object.getJsonObject("attributes").getJsonArray("customerId").get(0).toString().replace("\"", "");
                    break;
                }
            }

            return new Object[]{
                    new Token(tokenData.getString("access_token"), Token.TokenType.ACCESS_TOKEN, tokenData.getJsonNumber("expires_in").intValue()),
                    new Token(tokenData.getString("refresh_token"), Token.TokenType.REFRESH_TOKEN, tokenData.getJsonNumber("refresh_expires_in").intValue()),
                    accountId,
                    customerId
            };
        }catch (Exception e) {
            e.printStackTrace();
            throw  e;
        }
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

    //TODO: bone nashta heren e pare me password, po tani ruje refresh tokenin edhe boni me refresh token... deri sa skadon edhe refreshi
    public void generateNewAdminToken() {
        JsonObject data = keycloakRestClient.login("fisnikz", "123456", "password", keycloakClientId, null).readEntity(JsonObject.class);
        this.adminToken = new Token(data.getString("access_token"), Token.TokenType.ACCESS_TOKEN, data.getJsonNumber("expires_in").longValue());
    }

    public String loginAsRootUser(String clientId) {
        Response response = keycloakRestClient.loginAsRoot("admin", "admin", "password", clientId);
        if (response.getStatus() == 200) {
            JsonObject data = response
                    .readEntity(JsonObject.class);
            return data.getString("access_token");
        }
        throw new RuntimeException("Couldn't login as root user!");
    }

    public String getAdminToken() {
        return this.adminToken.getTokenString();
    }


    public Response logout(String refreshToken) {
        return keycloakRestClient.logout(refreshToken, keycloakClientId);
    }

    public Response createAccount(CreateCustomerRequest createCustomerRequest, String customerId) {
        JsonObject createAccountBody = keycloakJsonUserRepresentation(customerId, createCustomerRequest.getFirstName(), createCustomerRequest.getLastName(), createCustomerRequest.getUsername(), createCustomerRequest.getPassword(), "USERS");
        return keycloakRestClient.create(toBearerToken(getAdminToken()), createAccountBody);
    }

    public Response update(String customerId, UpdateCustomerRequest request) {
        JsonObject userJson = keycloakJsonUserRepresentation(customerId, request.getFirstName(), request.getFirstName(), request.getUsername(), request.getNewPassword(), "USERS");
        return keycloakRestClient.update(toBearerToken(getAdminToken()), request.getAccountId(), userJson);
    }

    private JsonObject keycloakJsonUserRepresentation(String customerId, String firstName, String lastName, String username, String password, String group) {
        JsonObjectBuilder customerIdAttribute = Json.createObjectBuilder()
                .add("customerId", Json.createArrayBuilder().add(customerId));

        JsonArrayBuilder credentialsArray = Json.createArrayBuilder();
        if (password != null) {
            JsonObjectBuilder passwordJson = Json.createObjectBuilder()
                    .add("type", "password")
                    .add("temporary", false)
                    .add("value", password);
            credentialsArray.add(passwordJson);
        }

        JsonObjectBuilder userRepresentation = Json.createObjectBuilder()
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("username", username)
                .add("emailVerified", true)
                .add("enabled", true)
                .add("groups", Json.createArrayBuilder().add(group))
                .add("attributes", customerIdAttribute);

        if (password != null) {
            userRepresentation.add("credentials", credentialsArray);
        }

        return userRepresentation.build();
    }

    public Response createAdminAccount(CreateCustomerRequest createCustomerRequest, String customerId, String accessToken) {
        JsonObject createAccountBody = keycloakJsonUserRepresentation(customerId, createCustomerRequest.getFirstName(), createCustomerRequest.getLastName(), createCustomerRequest.getUsername(), createCustomerRequest.getPassword(), "ADMINS");
        return keycloakRestClient.create(toBearerToken(accessToken), createAccountBody);
    }
}
