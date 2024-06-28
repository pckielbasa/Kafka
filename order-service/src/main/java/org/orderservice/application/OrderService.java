package org.orderservice.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.orderservice.application.event.OrderCreatedEvent;
import org.orderservice.application.event.OrderDeletedEvent;
import org.orderservice.domain.dto.CreateOrderCommon;
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
    public void addOrder(CreateOrderCommon createOrderCommon) throws JsonProcessingException {
        Order newOrder = Order.builder()
                .orderId(createOrderCommon.getOrderId())
                .customerId(createOrderCommon.getCustomerId())
                .amount(createOrderCommon.getAmount())
                .status(createOrderCommon.getStatus())
                .creationDate(LocalDateTime.now())
                .build();
        orderRepository.save(newOrder);
        eventPublisher.publishCreatedOrderEvent(new OrderCreatedEvent(newOrder.getOrderId(), newOrder.getAmount()));
    }

    @Transactional
    public void deleteOrder(String orderId) throws JsonProcessingException {
        orderRepository.deleteByOrderId(orderId);
        eventPublisher.publishDeletedOrderEvent(new OrderDeletedEvent(orderId));
    }

}
