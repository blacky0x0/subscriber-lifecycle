package com.github.blacky.subscriber_lifecycle.service;

import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.CallDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Call;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Subscriber;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Sms;
import com.github.blacky.subscriber_lifecycle.web.transfer.Status;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;

import static com.github.blacky.subscriber_lifecycle.jooq.enums.Status.Active;
import static com.github.blacky.subscriber_lifecycle.jooq.enums.Status.Blocked;
import static com.github.blacky.subscriber_lifecycle.jooq.tables.Call.CALL;
import static com.github.blacky.subscriber_lifecycle.jooq.tables.Subscriber.SUBSCRIBER;
import static org.jooq.impl.DSL.field;

@Service
public class SubscriberService {

    private static final int PRICE_CALL = 50;
    private static final int PRICE_SMS = 1;

    @Value("${subscriber.lifecycle.calls_number_limit:3}")
    private int callsNumberLimit;

    private final DSLContext ctx;
    private final SubscriberDao subscriberRepository;
    private final CallDao callRepository;

    @Autowired
    public SubscriberService(DSLContext ctx, SubscriberDao subscriberRepository, CallDao callRepository) {
        this.ctx = ctx;
        this.subscriberRepository = subscriberRepository;
        this.callRepository = callRepository;
    }

    /**
     * Make a call from one subscriber to another.
     * Update subscriber's balance and register a call.
     */
    @Transactional
    public void onCall(com.github.blacky.subscriber_lifecycle.web.transfer.Call callInfo) {
        Subscriber subscriber = subscriberRepository.fetchOne(field(SUBSCRIBER.MSISDN), callInfo.getFrom());

        if (subscriber == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        if (subscriber.getStatus() == Blocked) {
            throw new RuntimeException("Account is blocked. Operation denied");
        }

        int countCallsToday = ctx
                .selectCount()
                .from(CALL)
                .where(CALL.SUBSCRIBER_ID.eq(subscriber.getId())
                        .and(CALL.CREATED.cast(Date.class).eq(new Date(Instant.now().toEpochMilli()))))
                .fetchOneInto(Integer.class);

        if (countCallsToday > callsNumberLimit) {
            throw new RuntimeException("Call limit exceeded!");
        }

        Call call = new Call();
        call.setSubscriberId(subscriber.getId());
        callRepository.insert(call);

        subscriber.setBalance(subscriber.getBalance() - PRICE_CALL);
        subscriber.setUpdated(Timestamp.from(Instant.now()));
        if (subscriber.getBalance() < 0) {
            subscriber.setStatus(Blocked);
        }
        subscriberRepository.update(subscriber);
    }

    /**
     * Send a text message from one subscriber to another.
     * Update subscriber's balalnce.
     */
    public void onSms(Sms sms) {
        Subscriber subscriber = subscriberRepository.fetchOne(field(SUBSCRIBER.MSISDN), sms.getFrom());
        if (subscriber == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        if (subscriber.getStatus() == Blocked) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        subscriber.setBalance(subscriber.getBalance() - PRICE_SMS);
        subscriber.setUpdated(Timestamp.from(Instant.now()));
        if (subscriber.getBalance() < 0) {
            subscriber.setStatus(Blocked);
        }
        subscriberRepository.update(subscriber);
    }

    /**
     * Get subscriber's account information by specified msisdn
     */
    public Account getAccount(String msisdn) {
        Subscriber subscriber = subscriberRepository.fetchOne(field(SUBSCRIBER.MSISDN), msisdn);
        if (subscriber == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return new Account(subscriber.getBalance(), Status.of(subscriber.getStatus()));
    }

    /**
     * Populate subscriber's account with a specified amount
     */
    public void makeDeposit(Deposit deposit) {
        Subscriber subscriber = subscriberRepository.fetchOne(field(SUBSCRIBER.MSISDN), deposit.getMsisdn());
        if (subscriber == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        subscriber.setBalance(subscriber.getBalance() + deposit.getAmount());
        subscriber.setUpdated(Timestamp.from(Instant.now()));
        if (subscriber.getStatus() == Blocked && subscriber.getBalance() >= 0) {
            subscriber.setStatus(Active);
        }

        subscriberRepository.update(subscriber);
    }

}
