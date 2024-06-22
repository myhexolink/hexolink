package com.messenger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.user.DefaultUserDestinationResolver;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.messaging.simp.user.UserDestinationResolver;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Configuration
@EnableWebSocket
@EnableWebSocketMessageBroker
class WebSocketConfigSpringSession implements WebSocketMessageBrokerConfigurer {

    private final String relayHost = "172.20.10.4";
    private final Integer relayPort = 61613;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setAllowedOrigins("*")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .addInterceptors(new HttpHandshakeInterceptor())
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableStompBrokerRelay("/queue/", "/topic/")
                .setUserDestinationBroadcast("/topic/unresolved-user-destination")
                .setUserRegistryBroadcast("/topic/simp-user-registry")
                .setSystemLogin("qqqqq")
                .setSystemPasscode("wwwww")
                .setClientLogin("eeeee")
                .setClientPasscode("rrrrr")
                .setRelayHost(relayHost)
                .setRelayPort(relayPort);
        registry.setApplicationDestinationPrefixes("/app");
        registry.setUserDestinationPrefix("/user");
    }

    public void configureWebSocketTransport(WebSocketTransportRegistration registration) {
        registration.setMessageSizeLimit(222222222);
        registration.setSendBufferSizeLimit(222222222);
    }
}
