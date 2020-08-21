package com.fisnikz.coffee_express.events.entity;

import com.fisnikz.coffee_express.finance.entity.PaymentInformation;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
public class Payment extends PanacheEntityBase {

    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @NotNull
    @Type(type = "uuid-char")
    public UUID customerId;

    @Embedded
    @NotNull
    public PaymentInformation paymentInformation;

}
