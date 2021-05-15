package com.fisnikz.coffee_express.customers.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.hibernate.annotations.Type;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
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

    @NotBlank
    public String firstName;

    @NotBlank
    public String lastName;

    @NotBlank
    public String username;

    @NotNull
    @JsonbDateFormat("d MMM y, HH:mm:ss")
    public LocalDateTime registeredAt;
    public boolean banned;

}
