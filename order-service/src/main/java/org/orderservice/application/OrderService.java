package org.orderservice.application;

import lombok.RequiredArgsConstructor;
import org.orderservice.domain.model.Order;
import org.orderservice.domain.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(Order order) {
        Order newOrder = new Order();
        newOrder.setOrderId(order.getOrderId());
        newOrder.setStatus(order.getStatus());
        newOrder.setCustomerId(order.getCustomerId());
        newOrder.setAmount(Double.max(order.getAmount(), 2));
        orderRepository.save(newOrder);
    }
}
