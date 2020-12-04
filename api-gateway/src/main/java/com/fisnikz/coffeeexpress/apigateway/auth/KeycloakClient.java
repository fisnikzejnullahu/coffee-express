package com.fisnikz.coffeeexpress.apigateway.auth;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

/**
 * @author Fisnik Zejnullahu
 */
public class KeycloakClient {

    private static KeycloakBuilder builder() {
        return KeycloakBuilder.builder()
                .serverUrl("http://localhost:9099/auth")
                .grantType(OAuth2Constants.PASSWORD)
                .realm("public")
                .clientId("coffee-express-admin-api-client");
    }

    public static Keycloak getAdminKeycloak() {
        return getUserKeycloak("fisnikz", "123456");
    }

    public static Keycloak getUserKeycloak(String username, String password) {
        return builder()
                .username(username)
                .password(password)
                .build();
    }
}
