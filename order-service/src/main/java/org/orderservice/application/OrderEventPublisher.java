package org.orderservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.orderservice.application.event.OrderCreatedEvent;
import org.orderservice.application.event.OrderEvent;
import org.orderservice.domain.model.Order;
import org.orderservice.infrastructure.kafka.OrderProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {

    private final String topic = "order-service";
    private final OrderProducer orderProducer;
    private final ObjectMapper objectMapper;

    private final String DELETE_ORDER_MESSAGE = "Order by id %s is deleted.";

    public void publish(final OrderEvent orderEvent) throws JsonProcessingException {
        if (orderEvent instanceof OrderCreatedEvent orderCreatedEvent){
            orderProducer.sendMessageToKafka(topic, orderEvent.getOrderID(), objectMapper.writeValueAsString(orderCreatedEvent));
            log.debug("Order by id {} is created.", orderEvent.getOrderID());
        }
    }

    public void sendDeleteOrderNotification(String orderId) {
        orderProducer.sendMessageToKafka(topic, orderId, String.format(DELETE_ORDER_MESSAGE, orderId) );
    }
}
