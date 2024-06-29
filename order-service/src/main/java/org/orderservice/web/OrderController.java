package org.orderservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.orderservice.application.service.OrderService;
import org.orderservice.domain.dto.CreateOrderCommon;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @PostMapping( "/create")
    void addOrder(@RequestBody CreateOrderCommon createOrderCommon) throws JsonProcessingException {
            orderService.addOrder(createOrderCommon);
    }

    @DeleteMapping("/delete-{OrderId}")
    void deleteOrderById(@PathVariable String OrderId) throws JsonProcessingException {
        orderService.deleteOrder(OrderId);
    }
}
