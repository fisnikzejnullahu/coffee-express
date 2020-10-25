package com.fisnikz.coffee_express.finance.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
public class BankAccount extends PanacheEntityBase {

    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @Type(type = "uuid-char")
    @NotNull
    public UUID customerId;

    @Embedded
    @NotNull
    public CreditCardInfo creditCardInfo;

    @JsonbTransient
    @OneToMany(mappedBy = "account")
    public List<Payment> payments;

    @Override
    public String toString() {
        return "BankAccount{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", creditCardInfo=" + creditCardInfo +
                '}';
    }
}
