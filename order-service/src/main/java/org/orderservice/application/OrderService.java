package org.orderservice.application;

import lombok.RequiredArgsConstructor;
import org.orderservice.domain.model.Order;
import org.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder() {
        Order newOrder = new Order();
        newOrder.setOrderId("1");
        newOrder.setStatus("Start");
        newOrder.setCustomerId(1);
        newOrder.setAmount(new BigDecimal("100.10"));
        orderRepository.save(newOrder);
    }
}
