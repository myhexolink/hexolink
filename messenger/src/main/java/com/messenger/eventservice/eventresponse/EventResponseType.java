package com.messenger.eventservice.eventresponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventResponseType {
    USER_UPDATED(Constants.USER_UPDATED_CONSTANT_STRING);

    EventResponseType(String event) {
    }

    public static class Constants {
        public static final String USER_UPDATED_CONSTANT_STRING = "USER_UPDATED";
    }
}
