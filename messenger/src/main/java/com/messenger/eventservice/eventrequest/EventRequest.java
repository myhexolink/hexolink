package com.messenger.eventservice.eventrequest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.messenger.eventservice.usereventservice.UserEventService;
import lombok.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes(
        {
                @JsonSubTypes.Type(
                        value = UserEventService.class,
                        name = EventRequestType.Constants.USER_UPDATE_CONSTANT_STRING
                ),
        }
)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class EventRequest {
    private EventRequestType type;

    public EventRequest(EventRequestType type) {
        this.type = type;
    }
}
