package com.github.blacky.subscriber_lifecycle.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SubscriberLimitExceededException extends SubscriberException {
    private final HttpStatus status = HttpStatus.TOO_MANY_REQUESTS;

    public SubscriberLimitExceededException(String object) {
        super(String.format("Limit exceeded for %s", object));
    }
}
