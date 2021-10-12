package com.fisnikz.coffee_express.identity.control;

import com.fisnikz.coffee_express.customers.entity.CreateCustomerRequest;
import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.lang.System.Logger;

/**
 * @author Fisnik Zejnullahu
 */
@RequestScoped
public class CreateInitializerAdmin {

    @Inject
    KeycloakService keycloakService;

    @Inject
    @ConfigProperty(name = "INIT_USER_CUSTOMER_ID")
    String customerId;

    @Inject
    @ConfigProperty(name = "KEYCLOAK_MASTER_REALM_CLIENT_ID")
    String keycloakClientId;

    @Inject
    Logger LOG;

    @Transactional
    void onStart(@Observes StartupEvent event) {
        LOG.log(Logger.Level.INFO, "Creating initial admin user in public realm!");
        String rootUserAccessToken = keycloakService.loginAsRootUser(keycloakClientId);
        try {
            Response response = keycloakService.createAdminAccount(new CreateCustomerRequest("Fisnik", "Zejnullahu", "fisnikz", "123456"), customerId, rootUserAccessToken);
            if (response.getStatus() == 201) {
                LOG.log(Logger.Level.INFO, "Admin user in public realm has been created successfully!");
                keycloakService.generateNewAdminToken();
            }
        } catch (WebApplicationException ex) {
            if (ex.getResponse().getStatus() == 409) {
                LOG.log(Logger.Level.INFO, "Admin user in public realm exists!");
            } else {
                LOG.log(Logger.Level.ERROR, "Initial user couldn't be created!");
                throw ex;
            }
        }
    }
}
