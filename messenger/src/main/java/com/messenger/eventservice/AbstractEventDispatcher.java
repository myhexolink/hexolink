package com.messenger.eventservice;

import com.messenger.eventservice.eventrequest.EventRequest;
import com.messenger.eventservice.eventresponse.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface AbstractEventDispatcher<O extends EventRequest> {

    List<EventResponse> startEvent(O eventRequest, Integer fromUserId, String authorizationHeader);

    String getType();

    @Autowired
    default void registerMyself(EventProcessor mailSender) {
        mailSender.register(getType(), this);
    }
}
