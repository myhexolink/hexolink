package com.messenger.eventservice.eventresponse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class EventResponse {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer fromUserId;
    private EventResponseType type;

    public EventResponse(EventResponseType type) {
        this.type = type;
    }
}
