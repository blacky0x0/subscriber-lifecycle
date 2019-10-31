package com.github.blacky.subscriber_lifecycle.web.transfer;

import lombok.Data;

@Data
public class Message {
    private String to;
    private String from;
    private String text;
}
