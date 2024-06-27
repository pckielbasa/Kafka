package org.orderservice.application;

import lombok.RequiredArgsConstructor;
import org.orderservice.domain.model.Order;
import org.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(Order order) {
        Order newOrder = Order.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomerId())
                .amount(order.getAmount())
                .status(order.getStatus())
                .creationDate(LocalDateTime.now())
                .build();
        orderRepository.save(newOrder);
    }
}
