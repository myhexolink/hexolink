package com.messenger.eventservice.usereventservice;

import com.messenger.eventservice.AbstractEventDispatcher;
import com.messenger.eventservice.eventrequest.EventUpdateUserRequest;
import com.messenger.eventservice.eventresponse.EventResponse;
import com.messenger.eventservice.eventresponse.EventUpdateUserResponse;
import com.messenger.to.UserTo;
import com.messenger.util.exception.EventSendingError;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static com.messenger.eventservice.eventrequest.EventRequestType.USER_UPDATE;
import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@Service
public class UserEventService implements AbstractEventDispatcher<EventUpdateUserRequest> {

    @Value("${host}")
    public String host;

    @Override
    public List<EventResponse> startEvent(EventUpdateUserRequest eventRequest, Integer fromUserId, String authorizationHeader) {
        ResponseEntity<Object> responseEntity = updateUserRestService(eventRequest.getUserTo(), authorizationHeader);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return Collections.singletonList(EventUpdateUserResponse.builder().build());
        } else {
            throw new EventSendingError("EventSendingError");
        }
    }

    ResponseEntity<Object> updateUserRestService(UserTo updatedFields, String authorizationHeader) {
        HttpEntity<Object> entity = new HttpEntity<>(updatedFields, buildHeader(authorizationHeader));
        return new RestTemplate()
                .exchange("https://hexolink.lat/user-profile", HttpMethod.PUT, entity, Object.class);
    }

    private HttpHeaders buildHeader(String authorizationHeader) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set(AUTHORIZATION_HEADER_NAME, authorizationHeader);
        return headers;
    }

    @Override
    public String getType() {
        return USER_UPDATE.name();
    }
}
