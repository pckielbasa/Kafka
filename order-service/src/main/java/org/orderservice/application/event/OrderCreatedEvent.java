package org.orderservice.application.event;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderCreatedEvent extends OrderEvent {
    @JsonProperty
    private final String type = "OrderCreatedEvent";
    @JsonProperty
    private final Double amount;

    public OrderCreatedEvent(final String orderId, final Double amount) {
        this.orderID = orderId;
        this.amount = amount;
    }
}
