/*
 * This file is generated by jOOQ.
 */
package com.github.blacky.subscriber_lifecycle.jooq.tables.daos;


import com.github.blacky.subscriber_lifecycle.jooq.tables.Call;
import com.github.blacky.subscriber_lifecycle.jooq.tables.records.CallRecord;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


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
@Repository
public class CallDao extends DAOImpl<CallRecord, com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call, Long> {

    /**
     * Create a new CallDao without any configuration
     */
    public CallDao() {
        super(Call.CALL, com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call.class);
    }

    /**
     * Create a new CallDao with an attached configuration
     */
    @Autowired
    public CallDao(Configuration configuration) {
        super(Call.CALL, com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call.class, configuration);
    }

    @Override
    public Long getId(com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchRangeOfId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Call.CALL.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchById(Long... values) {
        return fetch(Call.CALL.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call fetchOneById(Long value) {
        return fetchOne(Call.CALL.ID, value);
    }

    /**
     * Fetch records that have <code>subscriber_id BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchRangeOfSubscriberId(Long lowerInclusive, Long upperInclusive) {
        return fetchRange(Call.CALL.SUBSCRIBER_ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>subscriber_id IN (values)</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchBySubscriberId(Long... values) {
        return fetch(Call.CALL.SUBSCRIBER_ID, values);
    }

    /**
     * Fetch records that have <code>created BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchRangeOfCreated(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Call.CALL.CREATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>created IN (values)</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchByCreated(Timestamp... values) {
        return fetch(Call.CALL.CREATED, values);
    }

    /**
     * Fetch records that have <code>updated BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchRangeOfUpdated(Timestamp lowerInclusive, Timestamp upperInclusive) {
        return fetchRange(Call.CALL.UPDATED, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>updated IN (values)</code>
     */
    public List<com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call> fetchByUpdated(Timestamp... values) {
        return fetch(Call.CALL.UPDATED, values);
    }
}
