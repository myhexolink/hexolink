package com.messenger.eventservice;

import com.messenger.eventservice.eventrequest.EventRequest;
import com.messenger.eventservice.eventresponse.EventResponse;
import com.messenger.eventservice.eventsender.websocketeventsender.WebSocketSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EventProcessor {

    private WebSocketSender webSocketSender;

    @Autowired
    public EventProcessor(WebSocketSender webSocketSender) {
        this.webSocketSender = webSocketSender;
    }

    private Map<String, AbstractEventDispatcher> map = new HashMap<>();

    public void register(String code, AbstractEventDispatcher abstractEventDispatcher) {
        map.put(code, abstractEventDispatcher);
    }

    public void processEventAndSendMessage(EventRequest event, Integer fromUserId, String authorizationHeader) {
        AbstractEventDispatcher eventGenerator = map.get(event.getType().toString());
        List<EventResponse> list = eventGenerator.startEvent(event, fromUserId, authorizationHeader);
        list.forEach(eventResponse -> webSocketSender.sendUpdateUserMessage(fromUserId, eventResponse));
    }
}
