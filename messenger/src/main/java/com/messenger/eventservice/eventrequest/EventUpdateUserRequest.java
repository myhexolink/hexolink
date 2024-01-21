package com.messenger.eventservice.eventrequest;

import com.messenger.to.UserTo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static com.messenger.eventservice.eventrequest.EventRequestType.USER_UPDATE;

@Getter
@Setter
public class EventUpdateUserRequest extends EventRequest {
    private UserTo userTo;

    @Builder
    public EventUpdateUserRequest(
            UserTo userTo
    ) {
        super(USER_UPDATE);
        this.userTo = userTo;
    }
}
