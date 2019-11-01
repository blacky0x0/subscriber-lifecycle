package com.github.blacky.subscriber_lifecycle.web.transfer;

public enum Status {
    Active, Blocked;

    public static Status of(com.github.blacky.subscriber_lifecycle.jooq.enums.Status status) {
        switch (status) {
            case Active:
                return Active;
            case Blocked:
                return Blocked;
            default:
                throw new RuntimeException(String.format("Unknown status: %s", status));
        }
    }
}
