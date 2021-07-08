package com.fisnikz.coffee_express.health;

import com.fisnikz.coffee_express.identity.control.KeycloakRestClient;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * @author Fisnik Zejnullahu
 */
@Readiness
@ApplicationScoped
public class KeycloakHealthProbe implements HealthCheck {

    @Inject
    @RestClient
    KeycloakRestClient keycloakRestClient;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("keycloak").
                state(this.keycloakHealth()).
                build();
    }

    boolean keycloakHealth() {
        try {
            Response response = this.keycloakRestClient.healthCheck();
            return response.getStatus() == 200;
        } catch (Exception ex) {
            return false;
        }
    }
}
