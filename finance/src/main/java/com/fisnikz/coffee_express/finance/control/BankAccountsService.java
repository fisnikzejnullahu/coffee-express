package com.fisnikz.coffee_express.finance.control;

import com.fisnikz.coffee_express.finance.entity.BankAccount;
import com.fisnikz.coffee_express.finance.entity.Payment;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
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

    public void create(BankAccount account, String authorizedCustomerId) {
        checkForAuthorizedCustomerId(account.customerId, authorizedCustomerId);
        Response customerServiceResponse = restClient.find(account.customerId);
        metricRegistry.counter("customer_service_find_status_" + customerServiceResponse.getStatus()).inc();
        if (customerServiceResponse.getStatus() == 404) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).header("cause", customerServiceResponse.getHeaderString("cause")).build());
        } else {
            account.persist();
        }
    }

    public BankAccount find(UUID id, String authorizedCustomerId) {
        BankAccount bankAccount = BankAccount.findById(id);
        checkForAuthorizedCustomerId(bankAccount.customerId, authorizedCustomerId);
        return bankAccount;
    }

    public BankAccount getMostPopular(UUID customerId, String authorizedCustomerId) {
        checkForAuthorizedCustomerId(customerId, authorizedCustomerId);
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
        Root<BankAccount> from = query.from(BankAccount.class);
        Join<BankAccount, Payment> payments = from.join("payments");
        CriteriaQuery<Object[]> bankAccountCriteriaQuery =
                query.multiselect(from, cb.count(payments))
                        .where(cb.equal(from.get("customerId"), customerId))
                        .groupBy(from.get("id"))
                        .orderBy(cb.desc(cb.count(payments)));

        List<Object[]> rs = em.createQuery(bankAccountCriteriaQuery).getResultList();
        if (rs.size() == 0) return BankAccount.findAll().firstResult();
        Object[] data = rs.get(0);
        return (BankAccount) data[0];
    }

    public List<BankAccount> accountsOfCustomer(UUID customerId, String authorizedCustomerId) {
        checkForAuthorizedCustomerId(customerId, authorizedCustomerId);
        return BankAccount.find("customerId", customerId).list();
    }

    public boolean delete(UUID accountId, String authorizedCustomerId) {
        find(accountId, authorizedCustomerId);
        return BankAccount.deleteById(accountId);
    }

    private void checkForAuthorizedCustomerId(UUID customerId, String authorizedCustomerId) {
        if (authorizedCustomerId != null) {
            if (!customerId.equals(UUID.fromString(authorizedCustomerId))) {
                throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }
}
