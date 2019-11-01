package com.github.blacky.subscriber_lifecycle.service;

import com.github.blacky.subscriber_lifecycle.jooq.tables.daos.SubscriberDao;
import com.github.blacky.subscriber_lifecycle.jooq.tables.pojos.Subscriber;
import com.github.blacky.subscriber_lifecycle.web.transfer.*;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import static com.github.blacky.subscriber_lifecycle.jooq.enums.Status.*;
import static com.github.blacky.subscriber_lifecycle.jooq.tables.Subscriber.SUBSCRIBER;
import static org.jooq.impl.DSL.field;

@Service
public class SubscriberService {

    @Value("${subscriber.lifecycle.calls_number_limit:3}")
    private int callsNumberLimit;
    private final DSLContext ctx;
    private final SubscriberDao repository;

    @Autowired
    public SubscriberService(DSLContext ctx, SubscriberDao repository) {
        this.ctx = ctx;
        this.repository = repository;
    }

    public Object onCall(Call call) {

        Subscriber to = repository.fetchOne(field(SUBSCRIBER.MSISDN), call.getTo());
        Subscriber from = repository.fetchOne(field(SUBSCRIBER.MSISDN), call.getFrom());

        if (to == null || from == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND); // 404
        }

        if (from.getBalance() < 0) {
            throw new RuntimeException("Negative balance. Operation denied");
        }

        if (from.getStatus() == Blocked) {
            throw new RuntimeException("Account is blocked. Operation denied");
        }

        return null;
    }

    public Object onSms(Message message) {
        return null;
    }

    public Account getAccount(String msisdn) {
        Subscriber subscriber = repository.fetchOne(field(SUBSCRIBER.MSISDN), msisdn);
        if (subscriber == null) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
        return new Account(subscriber.getBalance(), Status.of(subscriber.getStatus()));
    }

    public Object makeDeposit(Deposit deposit) {
        return null;
    }

}
