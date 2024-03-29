/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables.records;


import com.github.blacky.subscriber_lifecycle.jooq.tables.Call;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
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
public class CallRecord extends UpdatableRecordImpl<CallRecord> implements Record4<Long, Long, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1150598989;

    /**
     * Setter for <code>public.call.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.call.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.call.subscriber_id</code>.
     */
    public void setSubscriberId(Long value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.call.subscriber_id</code>.
     */
    public Long getSubscriberId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>public.call.created</code>.
     */
    public void setCreated(Timestamp value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.call.created</code>.
     */
    public Timestamp getCreated() {
        return (Timestamp) get(2);
    }

    /**
     * Setter for <code>public.call.updated</code>.
     */
    public void setUpdated(Timestamp value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.call.updated</code>.
     */
    public Timestamp getUpdated() {
        return (Timestamp) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, Long, Timestamp, Timestamp> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Call.CALL.ID;
    }

    @Override
    public Field<Long> field2() {
        return Call.CALL.SUBSCRIBER_ID;
    }

    @Override
    public Field<Timestamp> field3() {
        return Call.CALL.CREATED;
    }

    @Override
    public Field<Timestamp> field4() {
        return Call.CALL.UPDATED;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Long component2() {
        return getSubscriberId();
    }

    @Override
    public Timestamp component3() {
        return getCreated();
    }

    @Override
    public Timestamp component4() {
        return getUpdated();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Long value2() {
        return getSubscriberId();
    }

    @Override
    public Timestamp value3() {
        return getCreated();
    }

    @Override
    public Timestamp value4() {
        return getUpdated();
    }

    @Override
    public CallRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CallRecord value2(Long value) {
        setSubscriberId(value);
        return this;
    }

    @Override
    public CallRecord value3(Timestamp value) {
        setCreated(value);
        return this;
    }

    @Override
    public CallRecord value4(Timestamp value) {
        setUpdated(value);
        return this;
    }

    @Override
    public CallRecord values(Long value1, Long value2, Timestamp value3, Timestamp value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CallRecord
     */
    public CallRecord() {
        super(Call.CALL);
    }

    /**
     * Create a detached, initialised CallRecord
     */
    public CallRecord(Long id, Long subscriberId, Timestamp created, Timestamp updated) {
        super(Call.CALL);

        set(0, id);
        set(1, subscriberId);
        set(2, created);
        set(3, updated);
    }
}
