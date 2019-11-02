package com.github.blacky.subscriber_lifecycle.web;

import com.github.blacky.subscriber_lifecycle.service.SubscriberService;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Call;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Sms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class SubscriberController {

    private final SubscriberService service;

    @Autowired
    public SubscriberController(SubscriberService service) {
        this.service = service;
    }

    @PostMapping("/call")
    public void onCall(@RequestBody Call call) {
        service.onCall(call);
    }

    @PostMapping("/sms")
    public void onSms(@RequestBody Sms sms) {
        service.onSms(sms);
    }

    @GetMapping("/account/{msisdn}")
    public Account getAccount(@PathVariable String msisdn) {

        if (msisdn == null || msisdn.trim().isEmpty()) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }

        return service.getAccount(msisdn);
    }

    @PostMapping("/account/deposit")
    public void makeDeposit(@RequestBody Deposit deposit) {
        service.makeDeposit(deposit);
    }

}
