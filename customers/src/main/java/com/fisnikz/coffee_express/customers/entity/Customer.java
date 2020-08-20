package com.fisnikz.coffee_express.customers.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Fisnik Zejnullahu
 */
@Entity
public class Customer extends PanacheEntityBase {

    @Id
    @Type(type = "uuid-char")
    public UUID id;

    @Embedded
    public CustomerName fullName;

    @NotNull
    public String nickname;

    @NotNull
    public LocalDateTime registeredAt;

    public boolean banned;

}
