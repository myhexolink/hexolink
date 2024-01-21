package com.messenger.eventservice.eventsender.websocketeventsender;

import com.messenger.eventservice.eventresponse.EventResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpSession;
import org.springframework.messaging.simp.user.SimpUser;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;

@Service
public class WebsocketEventSenderImpl implements WebSocketSender {



    private final SimpMessagingTemplate webSocketMessagingTemplate;

    @Autowired
    private SimpUserRegistry userRegistry;

    public Set<SimpSession> getUserSessions(Integer toUserId) {
        SimpUser simpUser = userRegistry.getUsers().stream().filter(user -> user.getName().equals(toUserId.toString())).findFirst().orElseGet(() -> null);
        if (simpUser == null) {
            return Set.of();
        }
        return simpUser.getSessions();
    }

    @Autowired
    public WebsocketEventSenderImpl(SimpMessagingTemplate webSocketMessagingTemplate) {
        this.webSocketMessagingTemplate = webSocketMessagingTemplate;
    }

    private void sendPrivateMessage(Integer toUserId, EventResponse eventResponse) {
//        getUserSessions(toUserId).forEach(session -> {
            SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
//            headerAccessor.setSessionId(session.getId());
            webSocketMessagingTemplate.convertAndSendToUser(toUserId.toString(), "/queue/private.messages", eventResponse);
//        });;
    }

    @Override
    public void sendUpdateUserMessage(Integer userId, EventResponse eventResponse) {
        sendPrivateMessage(userId, eventResponse);
    }
}
