package com.github.blacky.subscriber_lifecycle.advice;

import com.github.blacky.subscriber_lifecycle.exception.SubscriberException;
import com.github.blacky.subscriber_lifecycle.web.SubscriberController;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice(basePackageClasses = SubscriberController.class)
public class SubscriberControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubscriberException.class)
    @ResponseBody
    ResponseEntity<ErrorMessage> handleSubscriberControllerException(SubscriberException ex) {
        return new ResponseEntity<>(new ErrorMessage(ex.getStatus().value(), ex.getMessage()), ex.getStatus());
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    private static class ErrorMessage {
        private int code;
        private String message;
    }
}
