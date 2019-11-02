package com.github.blacky.subscriber_lifecycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SubscriberBlockedException extends SubscriberException {
    private final HttpStatus status = HttpStatus.FORBIDDEN;

    public SubscriberBlockedException(String object) {
        super(String.format("Account %s is blocked. Operation denied", object));
    }
}
