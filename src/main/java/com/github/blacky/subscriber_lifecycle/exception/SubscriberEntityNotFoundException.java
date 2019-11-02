package com.github.blacky.subscriber_lifecycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SubscriberEntityNotFoundException extends SubscriberException {
    private final HttpStatus status = HttpStatus.NOT_FOUND;

    public SubscriberEntityNotFoundException(String object) {
        super(String.format("Account %s not found", object));
    }
}
