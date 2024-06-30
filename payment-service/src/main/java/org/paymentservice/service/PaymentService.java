package org.paymentservice.service;

import lombok.RequiredArgsConstructor;
import org.paymentservice.kafka.PaymentProducer;
import org.paymentservice.model.Payment;
import org.springframework.stereotype.Service;
import org.paymentservice.repository.PaymentMongoRepository;
import org.paymentservice.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService implements PaymentRepository {

    private final PaymentMongoRepository paymentMongoRepository;
    private final PaymentProducer paymentProducer;

    public void createPayment(Payment payment) {
        Payment newPayment = Payment.builder()
                .orderId("1")
                .type("BLIK")
                .cardNumber(123123123L)
                .validThru("12/123")
                .ccv(123)
                .phoneNumber(123123123L)
                .email("email@email.com")
                .status("InProgress")
                .build();
        paymentMongoRepository.save(newPayment);
        paymentProducer.sendMessageToKafka("payment-service", newPayment.getOrderId(), newPayment.getOrderId());
    }
}
