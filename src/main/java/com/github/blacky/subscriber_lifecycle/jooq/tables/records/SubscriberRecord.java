/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables.records;


import com.github.blacky.subscriber_lifecycle.jooq.enums.Status;
import com.github.blacky.subscriber_lifecycle.jooq.tables.Subscriber;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


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
public class SubscriberRecord extends UpdatableRecordImpl<SubscriberRecord> implements Record8<Long, String, String, String, Long, Status, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1428344917;

    /**
     * Setter for <code>public.subscriber.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.subscriber.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.subscriber.first_name</code>.
     */
    public void setFirstName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.subscriber.first_name</code>.
     */
    @NotNull
    public String getFirstName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.subscriber.last_name</code>.
     */
    public void setLastName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.subscriber.last_name</code>.
     */
    @NotNull
    public String getLastName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.subscriber.msisdn</code>.
     */
    public void setMsisdn(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.subscriber.msisdn</code>.
     */
    @NotNull
    public String getMsisdn() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.subscriber.balance</code>.
     */
    public void setBalance(Long value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.subscriber.balance</code>.
     */
    public Long getBalance() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>public.subscriber.status</code>.
     */
    public void setStatus(Status value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.subscriber.status</code>.
     */
    public Status getStatus() {
        return (Status) get(5);
    }

    /**
     * Setter for <code>public.subscriber.created</code>.
     */
    public void setCreated(Timestamp value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.subscriber.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(6);
    }

    /**
     * Setter for <code>public.subscriber.updated</code>.
     */
    public void setUpdated(Timestamp value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.subscriber.updated</code>.
     */
    public Timestamp getUpdated() {
        return (Timestamp) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Long, Status, Timestamp, Timestamp> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Long, String, String, String, Long, Status, Timestamp, Timestamp> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Subscriber.SUBSCRIBER.ID;
    }

    @Override
    public Field<String> field2() {
        return Subscriber.SUBSCRIBER.FIRST_NAME;
    }

    @Override
    public Field<String> field3() {
        return Subscriber.SUBSCRIBER.LAST_NAME;
    }

    @Override
    public Field<String> field4() {
        return Subscriber.SUBSCRIBER.MSISDN;
    }

    @Override
    public Field<Long> field5() {
        return Subscriber.SUBSCRIBER.BALANCE;
    }

    @Override
    public Field<Status> field6() {
        return Subscriber.SUBSCRIBER.STATUS;
    }

    @Override
    public Field<Timestamp> field7() {
        return Subscriber.SUBSCRIBER.CREATED;
    }

    @Override
    public Field<Timestamp> field8() {
        return Subscriber.SUBSCRIBER.UPDATED;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getFirstName();
    }

    @Override
    public String component3() {
        return getLastName();
    }

    @Override
    public String component4() {
        return getMsisdn();
    }

    @Override
    public Long component5() {
        return getBalance();
    }

    @Override
    public Status component6() {
        return getStatus();
    }

    @Override
    public Timestamp component7() {
        return getCreated();
    }

    @Override
    public Timestamp component8() {
        return getUpdated();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getFirstName();
    }

    @Override
    public String value3() {
        return getLastName();
    }

    @Override
    public String value4() {
        return getMsisdn();
    }

    @Override
    public Long value5() {
        return getBalance();
    }

    @Override
    public Status value6() {
        return getStatus();
    }

    @Override
    public Timestamp value7() {
        return getCreated();
    }

    @Override
    public Timestamp value8() {
        return getUpdated();
    }

    @Override
    public SubscriberRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public SubscriberRecord value2(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public SubscriberRecord value3(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public SubscriberRecord value4(String value) {
        setMsisdn(value);
        return this;
    }

    @Override
    public SubscriberRecord value5(Long value) {
        setBalance(value);
        return this;
    }

    @Override
    public SubscriberRecord value6(Status value) {
        setStatus(value);
        return this;
    }

    @Override
    public SubscriberRecord value7(Timestamp value) {
        setCreated(value);
        return this;
    }

    @Override
    public SubscriberRecord value8(Timestamp value) {
        setUpdated(value);
        return this;
    }

    @Override
    public SubscriberRecord values(Long value1, String value2, String value3, String value4, Long value5, Status value6, Timestamp value7, Timestamp value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached SubscriberRecord
     */
    public SubscriberRecord() {
        super(Subscriber.SUBSCRIBER);
    }

    /**
     * Create a detached, initialised SubscriberRecord
     */
    public SubscriberRecord(Long id, String firstName, String lastName, String msisdn, Long balance, Status status, Timestamp created, Timestamp updated) {
        super(Subscriber.SUBSCRIBER);

        set(0, id);
        set(1, firstName);
        set(2, lastName);
        set(3, msisdn);
        set(4, balance);
        set(5, status);
        set(6, created);
        set(7, updated);
    }
}