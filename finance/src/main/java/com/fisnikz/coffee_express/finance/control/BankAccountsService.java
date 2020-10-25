package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.finance.entity.Payment;
import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.annotation.RegistryType;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
    @Inject
    EntityManager em;

    public void create(BankAccount account) {
        Response customerServiceResponse = restClient.find(account.customerId);
        System.out.println(account.customerId + ", " + customerServiceResponse.getStatus());
        metricRegistry.counter("customer_service_find_status_" + customerServiceResponse.getStatus()).inc();
        if (customerServiceResponse.getStatus() == 404) {
            throw new NotFoundException("Customer was not found!");
        }
        account.persist();
    }

    public BankAccount find(UUID id) {
        return BankAccount.findById(id);
    }

    public BankAccount getMostPopular(UUID customerId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<BankAccount> from = query.from(BankAccount.class);
        Join<BankAccount, Payment> payments = from.join("payments");
        CriteriaQuery<Object[]> bankAccountCriteriaQuery =
                query.multiselect(from, cb.count(payments))
                        .where(cb.equal(from.get("customerId"), customerId))
                        .groupBy(from.get("id"))
                        .orderBy(cb.desc(cb.count(payments)));

        Object[] data = em.createQuery(bankAccountCriteriaQuery).getResultList().get(0);
        return (data.length == 0) ? null : (BankAccount) data[0];
    }
}
