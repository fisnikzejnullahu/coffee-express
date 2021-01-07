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
        if (bankAccount == null) {
            return null;
        }
        if (bankAccount.removed) {
            return null;
        }
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
                        .where(cb.equal(from.get("customerId"), customerId), cb.equal(from.get("removed"), false))
                        .groupBy(from.get("id"))
                        .orderBy(cb.desc(cb.count(payments)));

        List<Object[]> rs = em.createQuery(bankAccountCriteriaQuery).getResultList();
        System.out.println(rs.size());
        if (rs.size() != 0) {
            Object[] data = rs.get(0);
            return (BankAccount) data[0];
        }

        List<BankAccount> accounts = accountsOfCustomer(customerId, authorizedCustomerId, false);
        return accounts.size() != 0 ? accounts.get(0) : null;
    }

    //if admin jwt return all removed and non removed
    public List<BankAccount> accountsOfCustomer(UUID customerId, String authorizedCustomerId, boolean all) {
        if (all) {
            return BankAccount.list("customerId", customerId);
        }
        checkForAuthorizedCustomerId(customerId, authorizedCustomerId);
        return BankAccount.list("customerId = ?1 and removed = false", customerId);
    }

    public int delete(UUID accountId, String authorizedCustomerId) {
        BankAccount bankAccount = find(accountId, authorizedCustomerId);
        if (bankAccount == null) {
            throw new WebApplicationException(Response.status(404).build());
        }
        return BankAccount.update("removed = 1 where id = ?1", accountId);
    }

    private void checkForAuthorizedCustomerId(UUID customerId, String authorizedCustomerId) {
        if (authorizedCustomerId != null) {
            if (!customerId.equals(UUID.fromString(authorizedCustomerId))) {
                throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
    }
}
