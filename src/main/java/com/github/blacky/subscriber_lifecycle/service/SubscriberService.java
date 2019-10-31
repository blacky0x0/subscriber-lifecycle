package com.github.blacky.subscriber_lifecycle.service;

import com.github.blacky.subscriber_lifecycle.model.Status;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Call;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SubscriberService {

    @Value("${subscriber.lifecycle.calls_number_limit:3}")
    private int callsNumberLimit;


    public Object onCall(Call call) {
        return null;
    }

    public Object onSms(Message message) {
        return null;
    }

    public Account getAccount() {
        return new Account(0L, Status.Active);
    }

    public Object makeDeposit(Deposit deposit) {
        return null;
    }

}
