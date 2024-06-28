package org.orderservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.orderservice.application.event.OrderCreatedEvent;
import org.orderservice.domain.model.Order;
import org.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderEventPublisher eventPublisher;

    @Transactional
    public void addOrder(Order order) throws JsonProcessingException {
        Order newOrder = Order.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .amount(order.getAmount())
                .status(order.getStatus())
                .creationDate(LocalDateTime.now())
                .build();
        orderRepository.save(newOrder);
        eventPublisher.publish(new OrderCreatedEvent(newOrder.getOrderId(), newOrder.getAmount()));
    }

}
