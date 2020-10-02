package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.BankAccount;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@ApplicationScoped
@Transactional
public class BankAccountsService {

    @Inject
    @RestClient
    CustomersRestClient restClient;

    @Inject
    @RegistryType(type = MetricRegistry.Type.APPLICATION)
    MetricRegistry metricRegistry;

    public void create(BankAccount account) {
        Response customerServiceResponse = restClient.find(account.customerId);
        System.out.println(account.customerId + ", " + customerServiceResponse.getStatus());
        metricRegistry.counter("customer_service_find_status_" + customerServiceResponse.getStatus()).inc();
        if (customerServiceResponse.getStatus() == 404) {
            throw new NotFoundException("Customer was not found!");
        }
    }

    public BankAccount find(UUID id) {
        return BankAccount.findById(id);
    }
}
