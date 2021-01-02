package com.fisnikz.coffee_express;

import com.fisnikz.coffee_express.identity.control.KeycloakService;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
public class AdminClientRequest implements ClientRequestFilter {

    @Inject
    KeycloakService keycloakService;

    @Override
    public void filter(ClientRequestContext requestContext) {
        requestContext.getHeaders().add("Authorization", "Bearer " + keycloakService.getAdminToken());
    }
}
