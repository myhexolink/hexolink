package com.messenger.eventservice.eventsender.websocketeventsender;

import com.messenger.eventservice.eventresponse.EventResponse;

public interface WebSocketSender {
    void sendUpdateUserMessage(Integer userId, EventResponse eventResponse);
}
