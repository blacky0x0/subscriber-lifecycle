package com.github.blacky.subscriber_lifecycle.web;

import com.github.blacky.subscriber_lifecycle.service.SubscriberService;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Call;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class SubscriberController {

    private final SubscriberService service;

    @Autowired
    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping("/call")
    public Object onCall(Call call) {
        return service.onCall(call);
    }

    @PostMapping("/sms")
    public Object onSms(Message message) {
        return service.onSms(message);
    }

    @GetMapping("/account/{msisdn}")
    public Account getAccount(@PathVariable String msisdn) {

        if (msisdn == null || msisdn.trim().isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return service.getAccount(msisdn);
    }

    @PostMapping("/account/deposit")
    public Object makeDeposit(Deposit deposit) {
        return service.makeDeposit(deposit);
    }

}
