package com.fisnikz.coffee_express.identity.control;

import io.quarkus.scheduler.Scheduled;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
public class AdminTokenScheduledGenerator {

    @Inject
    KeycloakService keycloakService;

    @Scheduled(every="4m")
    void generateNewAdminToken() {
        keycloakService.generateNewAdminToken();
    }
}
