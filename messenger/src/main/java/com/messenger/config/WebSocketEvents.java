package com.messenger.config;

import com.messenger.entity.OAuthUser;
//import com.messenger.springtechnicservice.usersessionregistry.UserSessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WebSocketEvents {

    private final SimpUserRegistry userRegistry;

//    private final UserSessionRegistry userSessionRegistry;

    @Autowired
    public WebSocketEvents(SimpUserRegistry userRegistry/*, UserSessionRegistry userSessionRegistry*/) {
        this.userRegistry = userRegistry;
//        this.userSessionRegistry = userSessionRegistry;
    }

    //	@Async
    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        userSessionRegistry.getUserSessions(Integer.valueOf(event.getUser().getName())).stream()
//                .filter(session -> !sessionId.equals(session))
//                .forEach(session -> {
//                    String destination = "/user/" + accessor.getUser().getName() + "/queue/private";
//                    SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create();
//                    headerAccessor.setSessionId(session);
//                    headerAccessor.setDestination(destination);
//                    headerAccessor.setLeaveMutable(true);
//                    Message<byte[]> connectMessage = MessageBuilder.createMessage(new byte[0], headerAccessor.getMessageHeaders());
//                    channel.send(connectMessage);
//                });


//
//        int clientId = Integer.parseInt(headers.getNativeHeader("clientId").get(0));
//        Object[] objects = ((LinkedHashMap) (((OAuth2Authentication) SimpMessageHeaderAccessor.wrap(event.getMessage()).getUser()).getUserAuthentication()).getDetails()).entrySet().toArray();
//        Object value8 = ((Map.Entry) objects[8]).getValue();
//        Object value1 = ((Map.Entry) objects[1]).getValue();
//        int authenticatedClientId = Integer.parseInt(String.valueOf(value8));
//        String authenticatedClientUserName = String.valueOf(value1);
//        headers.getSessionAttributes().put("clientId", clientId);
    }

    //	@Async
    @EventListener
    public void handleSessionSubscribed(SessionSubscribeEvent event) {
//        SimpMessageHeaderAccessor headers = SimpMessageHeaderAccessor.wrap(event.getMessage());
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println(event.getMessage().getHeaders());
//        Integer id = ((OAuthUser) ((UsernamePasswordAuthenticationToken) ((OAuth2Authentication) headers.getUser()).getUserAuthentication()).getPrincipal()).getId();
//        event.getMessage().getHeaders().put("simpUser", id);
//        int clientId = Integer.parseInt(headers.getNativeHeader("clientId").get(0));
//        Object[] objects = ((LinkedHashMap) (((OAuth2Authentication) SimpMessageHeaderAccessor.wrap(event.getMessage()).getUser()).getUserAuthentication()).getDetails()).entrySet().toArray();
//        Object value8 = ((Map.Entry) objects[8]).getValue();
//        Object value1 = ((Map.Entry) objects[1]).getValue();
//        int authenticatedClientId = Integer.parseInt(String.valueOf(value8));
//        String authenticatedClientUserName = String.valueOf(value1);
//        headers.getSessionAttributes().put("clientId", clientId);
    }

    //	@Async
    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
//        Object[] objects = ((LinkedHashMap) ((OAuth2Authentication) event.getUser()).getUserAuthentication().getDetails()).entrySet().toArray();
//        Object value = ((Map.Entry) ((LinkedHashMap) ((Map.Entry) objects[2]).getValue()).entrySet().toArray()[0]).getValue();
//        Integer userId = Integer.valueOf(String.valueOf(value));
    }
}
