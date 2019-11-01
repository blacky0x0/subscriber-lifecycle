/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables;


import com.github.blacky.subscriber_lifecycle.jooq.Indexes;
import com.github.blacky.subscriber_lifecycle.jooq.Keys;
import com.github.blacky.subscriber_lifecycle.jooq.Public;
import com.github.blacky.subscriber_lifecycle.jooq.tables.records.CallRecord;

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
import org.jooq.Row4;
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
public class Call extends TableImpl<CallRecord> {

    private static final long serialVersionUID = 79837931;

    /**
     * The reference instance of <code>public.call</code>
     */
    public static final Call CALL = new Call();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CallRecord> getRecordType() {
        return CallRecord.class;
    }

    /**
     * The column <code>public.call.id</code>.
     */
    public final TableField<CallRecord, Long> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('call_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.call.subscriber_id</code>.
     */
    public final TableField<CallRecord, Long> SUBSCRIBER_ID = createField(DSL.name("subscriber_id"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('call_subscriber_id_seq'::regclass)", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>public.call.created</code>.
     */
    public final TableField<CallRecord, Timestamp> CREATED = createField(DSL.name("created"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * The column <code>public.call.updated</code>.
     */
    public final TableField<CallRecord, Timestamp> UPDATED = createField(DSL.name("updated"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false).defaultValue(org.jooq.impl.DSL.field("now()", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>public.call</code> table reference
     */
    public Call() {
        this(DSL.name("call"), null);
    }

    /**
     * Create an aliased <code>public.call</code> table reference
     */
    public Call(String alias) {
        this(DSL.name(alias), CALL);
    }

    /**
     * Create an aliased <code>public.call</code> table reference
     */
    public Call(Name alias) {
        this(alias, CALL);
    }

    private Call(Name alias, Table<CallRecord> aliased) {
        this(alias, aliased, null);
    }

    private Call(Name alias, Table<CallRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Call(Table<O> child, ForeignKey<O, CallRecord> key) {
        super(child, key, CALL);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.CALL_PKEY);
    }

    @Override
    public Identity<CallRecord, Long> getIdentity() {
        return Keys.IDENTITY_CALL;
    }

    @Override
    public UniqueKey<CallRecord> getPrimaryKey() {
        return Keys.CALL_PKEY;
    }

    @Override
    public List<UniqueKey<CallRecord>> getKeys() {
        return Arrays.<UniqueKey<CallRecord>>asList(Keys.CALL_PKEY);
    }

    @Override
    public List<ForeignKey<CallRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<CallRecord, ?>>asList(Keys.CALL__CALL_SUBSCRIBER_ID_FKEY);
    }

    public Subscriber subscriber() {
        return new Subscriber(this, Keys.CALL__CALL_SUBSCRIBER_ID_FKEY);
    }

    @Override
    public Call as(String alias) {
        return new Call(DSL.name(alias), this);
    }

    @Override
    public Call as(Name alias) {
        return new Call(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Call rename(String name) {
        return new Call(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Call rename(Name name) {
        return new Call(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, Long, Timestamp, Timestamp> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
