/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables.pojos;


import com.github.blacky.subscriber_lifecycle.jooq.enums.Status;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Subscriber implements Serializable {

    private static final long serialVersionUID = 1027455289;

    private Long      id;
    private String    firstName;
    private String    lastName;
    private String    msisdn;
    private Long      balance;
    private Status    status;
    private Timestamp created;
    private Timestamp updated;

    public Subscriber() {}

    public Subscriber(Subscriber value) {
        this.id = value.id;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.msisdn = value.msisdn;
        this.balance = value.balance;
        this.status = value.status;
        this.created = value.created;
        this.updated = value.updated;
    }

    public Subscriber(
        Long      id,
        String    firstName,
        String    lastName,
        String    msisdn,
        Long      balance,
        Status    status,
        Timestamp created,
        Timestamp updated
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.msisdn = msisdn;
        this.balance = balance;
        this.status = status;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public Long getBalance() {
        return this.balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Subscriber (");

        sb.append(id);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(msisdn);
        sb.append(", ").append(balance);
        sb.append(", ").append(status);
        sb.append(", ").append(created);
        sb.append(", ").append(updated);

        sb.append(")");
        return sb.toString();
    }
}
