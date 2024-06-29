package org.orderservice.application.publisher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.orderservice.application.event.OrderCreatedEvent;
import org.orderservice.application.event.OrderDeletedEvent;
import org.orderservice.application.event.OrderEvent;
import org.orderservice.infrastructure.kafka.OrderProducer;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventPublisher {

    private final String topic = "order-service";
    private final OrderProducer orderProducer;
    private final ObjectMapper objectMapper;

    public void publishCreatedOrderEvent(final OrderEvent orderEvent) throws JsonProcessingException {
        if (orderEvent instanceof OrderCreatedEvent orderCreatedEvent){
            orderProducer.sendMessageToKafka(topic, orderEvent.getOrderID(), objectMapper.writeValueAsString(orderCreatedEvent));
            log.debug("Order by id {} is created.", orderEvent.getOrderID());
        }
    }

    public void publishDeletedOrderEvent(final OrderEvent orderEvent) throws JsonProcessingException {
        if (orderEvent instanceof OrderDeletedEvent orderDeletedEvent){
            orderProducer.sendMessageToKafka(topic, orderEvent.getOrderID(), objectMapper.writeValueAsString(orderDeletedEvent));
            log.debug("Order by id {} is deleted.", orderEvent.getOrderID());
        }
    }

}
