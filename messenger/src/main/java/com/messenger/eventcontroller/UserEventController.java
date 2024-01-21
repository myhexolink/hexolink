package com.messenger.eventcontroller;

import com.messenger.entity.OAuthUser;
import com.messenger.eventservice.EventProcessor;
import com.messenger.eventservice.eventrequest.EventUpdateUserRequest;
import com.messenger.to.UserTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
public class UserEventController {

    private final EventProcessor eventProcessor;

    @Autowired
    public UserEventController(
            EventProcessor eventProcessor
    ) {
        this.eventProcessor = eventProcessor;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public OAuthUser getServiceUser(@AuthenticationPrincipal OAuthUser user) {
        return user;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(
            @Valid @RequestBody UserTo userTo,
            @AuthenticationPrincipal OAuthUser user,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        eventProcessor.processEventAndSendMessage(
                EventUpdateUserRequest.builder().
                        userTo(userTo)
                        .build(),
                user.getId(),
                authorizationHeader);
    }
}
