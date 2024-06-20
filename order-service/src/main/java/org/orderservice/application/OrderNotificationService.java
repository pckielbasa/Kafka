package org.orderservice.application;

import lombok.RequiredArgsConstructor;
import org.orderservice.domain.model.Order;
import org.orderservice.infrastructure.kafka.OrderProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderNotificationService {

    private final String topic = "order-service";
    private final OrderProducer orderProducer;

    private final String CREATE_ORDER_MESSAGE = "Order by id %s is created.";
    private final String DELETE_ORDER_MESSAGE = "Order by id %s is deleted.";

    public void sendCreateOrderNotification(String orderId) {
        orderProducer.sendMessageToKafka(topic, orderId, String.format(CREATE_ORDER_MESSAGE, orderId));
    }

    public void sendDeleteOrderNotification(String orderId) {
        orderProducer.sendMessageToKafka(topic, orderId, String.format(DELETE_ORDER_MESSAGE, orderId) );
    }
}
