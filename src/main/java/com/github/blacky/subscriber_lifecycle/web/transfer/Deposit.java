package com.github.blacky.subscriber_lifecycle.web.transfer;

import lombok.Data;

@Data
public class Deposit {
    private long amount;
    private String msisdn;
}
