package com.github.blacky.subscriber_lifecycle.model;

import lombok.Data;

@Data
public class Subscriber {

    private String firstName;
    private String lastName;
    private String msisdn;
    private long balance;
    private Status status;

}
