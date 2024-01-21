package com.messenger.util.exception;

public class EventSendingError extends RuntimeException {
    public EventSendingError(String message) {
        super(message);
    }
}
