package org.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.orderservice.application.OrderNotificationService;
import org.orderservice.application.OrderService;
import org.orderservice.domain.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="order")
@RequiredArgsConstructor
public class OrderController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderNotificationService orderNotificationService;
    private final OrderService orderService;

    @Transactional
    @PostMapping(path = "/create")
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        try {
            orderService.addOrder(order);
            orderNotificationService.sendCreateOrderNotification(order.getOrderId());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error while adding order by id{}", order.getOrderId(), e);
            throw new RuntimeException(e);
        }
    }
}
