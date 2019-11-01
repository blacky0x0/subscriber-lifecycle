/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables;


import com.github.blacky.subscriber_lifecycle.jooq.Indexes;
import com.github.blacky.subscriber_lifecycle.jooq.Keys;
import com.github.blacky.subscriber_lifecycle.jooq.Public;
import com.github.blacky.subscriber_lifecycle.jooq.enums.Status;
import com.github.blacky.subscriber_lifecycle.jooq.tables.records.SubscriberRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


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
public class Subscriber extends TableImpl<SubscriberRecord> {

    private static final long serialVersionUID = -1517421702;

    /**
     * The reference instance of <code>public.subscriber</code>
     */
    public static final Subscriber SUBSCRIBER = new Subscriber();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SubscriberRecord> getRecordType() {
        return SubscriberRecord.class;
    }

    /**
     * The column <code>public.subscriber.id</code>.
     */
    public final TableField<SubscriberRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('subscriber_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.subscriber.first_name</code>.
     */
    public final TableField<SubscriberRecord, String> FIRST_NAME = createField(DSL.name("first_name"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.subscriber.last_name</code>.
     */
    public final TableField<SubscriberRecord, String> LAST_NAME = createField(DSL.name("last_name"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.subscriber.msisdn</code>.
     */
    public final TableField<SubscriberRecord, String> MSISDN = createField(DSL.name("msisdn"), org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.subscriber.balance</code>.
     */
    public final TableField<SubscriberRecord, Long> BALANCE = createField(DSL.name("balance"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.subscriber.status</code>.
     */
    public final TableField<SubscriberRecord, Status> STATUS = createField(DSL.name("status"), org.jooq.impl.SQLDataType.VARCHAR.nullable(false).defaultValue(org.jooq.impl.DSL.field("'Active'::status", org.jooq.impl.SQLDataType.VARCHAR)).asEnumDataType(com.github.blacky.subscriber_lifecycle.jooq.enums.Status.class), this, "");

    /**
     * The column <code>public.subscriber.created</code>.
     */
    public final TableField<SubscriberRecord, Timestamp> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.subscriber.updated</code>.
     */
    public final TableField<SubscriberRecord, Timestamp> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>public.subscriber</code> table reference
     */
    public Subscriber() {
        this(DSL.name("subscriber"), null);
    }

    /**
     * Create an aliased <code>public.subscriber</code> table reference
     */
    public Subscriber(String alias) {
        this(DSL.name(alias), SUBSCRIBER);
    }

    /**
     * Create an aliased <code>public.subscriber</code> table reference
     */
    public Subscriber(Name alias) {
        this(alias, SUBSCRIBER);
    }

    private Subscriber(Name alias, Table<SubscriberRecord> aliased) {
        this(alias, aliased, null);
    }

    private Subscriber(Name alias, Table<SubscriberRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Subscriber(Table<O> child, ForeignKey<O, SubscriberRecord> key) {
        super(child, key, SUBSCRIBER);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.SUBSCRIBER_PKEY);
    }

    @Override
    public Identity<SubscriberRecord, Long> getIdentity() {
        return Keys.IDENTITY_SUBSCRIBER;
    }

    @Override
    public UniqueKey<SubscriberRecord> getPrimaryKey() {
        return Keys.SUBSCRIBER_PKEY;
    }

    @Override
    public List<UniqueKey<SubscriberRecord>> getKeys() {
        return Arrays.<UniqueKey<SubscriberRecord>>asList(Keys.SUBSCRIBER_PKEY);
    }

    @Override
    public Subscriber as(String alias) {
        return new Subscriber(DSL.name(alias), this);
    }

    @Override
    public Subscriber as(Name alias) {
        return new Subscriber(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Subscriber rename(String name) {
        return new Subscriber(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Subscriber rename(Name name) {
        return new Subscriber(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Long, String, String, String, Long, Status, Timestamp, Timestamp> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}