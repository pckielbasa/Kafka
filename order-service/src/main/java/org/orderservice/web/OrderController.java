package org.orderservice.web;

import lombok.RequiredArgsConstructor;
import org.orderservice.application.OrderNotificationService;
import org.orderservice.application.OrderService;
import org.orderservice.domain.model.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        } catch (DataAccessException dae) {
            logger.error("Database error while adding order with id {}", order.getOrderId(), dae);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
        } catch (Exception e) {
            logger.error("Unexpected error while adding order with id {}", order.getOrderId(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }
}
