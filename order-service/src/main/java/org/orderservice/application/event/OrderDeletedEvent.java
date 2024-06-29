package org.orderservice.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderDeletedEvent extends OrderEvent {
    @JsonProperty
    private final String type = "OrderDeletedEvent";

    public OrderDeletedEvent(final String orderId) {
        this.orderID = orderId;
    }
}
