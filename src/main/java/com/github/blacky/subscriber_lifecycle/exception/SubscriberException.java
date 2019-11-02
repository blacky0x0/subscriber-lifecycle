package com.github.blacky.subscriber_lifecycle.exception;

import org.springframework.http.HttpStatus;

public abstract class SubscriberException extends IllegalArgumentException {
    SubscriberException(String s) {
        super(s);
    }

    public abstract HttpStatus getStatus();
}
