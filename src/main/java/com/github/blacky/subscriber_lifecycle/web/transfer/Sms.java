package com.github.blacky.subscriber_lifecycle.web.transfer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Sms {
    private String to;
    private String from;
    private String text;
}
