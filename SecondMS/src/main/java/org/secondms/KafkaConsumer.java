package org.secondms;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final String TOPIC = "test-topic";

    @KafkaListener(topics = TOPIC, groupId = "secondMS")
    public void consume(String message) {
        System.out.printf("Consumed message: %s%n", message);
    }
}
