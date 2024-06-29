package org.orderservice.infrastructure.kafka;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessageToKafka(String topic, String key, String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                exception.printStackTrace(
                        System.err
                );
            } else {
                RecordMetadata recordMetadata = sendResult.getRecordMetadata();
                System.out.println("The offset of the record we just sent is: " + recordMetadata.offset());
            }
        });
    }
}
