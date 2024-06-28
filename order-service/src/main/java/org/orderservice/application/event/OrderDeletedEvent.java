package org.orderservice.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderDeletedEvent extends OrderEvent {
    @JsonProperty
    private final String type = "OrderDeletedEvent";

    public OrderDeletedEvent(String order) {
        super();
    }
}
