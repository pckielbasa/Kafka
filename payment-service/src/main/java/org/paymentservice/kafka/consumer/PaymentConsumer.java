package org.paymentservice.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    @KafkaListener(topics = "order-service", groupId = "payment-service")
    public void listen(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
