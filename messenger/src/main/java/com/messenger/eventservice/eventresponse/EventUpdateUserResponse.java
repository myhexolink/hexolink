package com.messenger.eventservice.eventresponse;

import lombok.Builder;

import static com.messenger.eventservice.eventresponse.EventResponseType.USER_UPDATED;

public class EventUpdateUserResponse extends EventResponse {
    @Builder
    public EventUpdateUserResponse() {
        super(USER_UPDATED);
    }
}
