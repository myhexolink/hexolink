package com.messenger.eventservice.eventrequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum EventRequestType {
    USER_UPDATE(Constants.USER_UPDATE_CONSTANT_STRING);

    EventRequestType(String event) {
    }

    public static class Constants {
        public static final String USER_UPDATE_CONSTANT_STRING = "USER_UPDATE";
    }
}
