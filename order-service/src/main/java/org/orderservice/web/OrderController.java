package org.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.orderservice.application.OrderService;
import org.orderservice.infrastructure.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderProducer orderProducer;


    @PostMapping(path = "/add")
    void addOrder() {
        orderProducer.sendMessageToKafka("order-service","Test","test");
    }
}
