package com.github.blacky.subscriber_lifecycle.web;

import com.github.blacky.subscriber_lifecycle.service.SubscriberService;
import com.github.blacky.subscriber_lifecycle.web.transfer.Account;
import com.github.blacky.subscriber_lifecycle.web.transfer.Call;
import com.github.blacky.subscriber_lifecycle.web.transfer.Deposit;
import com.github.blacky.subscriber_lifecycle.web.transfer.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/account")
    public Account getAccount() {
        return service.getAccount();
    }

    @PostMapping("/account/deposit")
    public Object makeDeposit(Deposit deposit) {
        return service.makeDeposit(deposit);
    }

}
