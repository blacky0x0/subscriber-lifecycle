package com.github.blacky.subscriber_lifecycle.web.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Deposit {
    private long amount;
    private String msisdn;
}
