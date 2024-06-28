package org.orderservice.application.event;

import lombok.Getter;

@Getter
public abstract class OrderEvent {
    protected String orderID;
}

