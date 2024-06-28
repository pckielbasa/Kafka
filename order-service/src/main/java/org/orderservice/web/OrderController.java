package org.orderservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.orderservice.application.OrderService;
import org.orderservice.domain.dto.CreateOrderCommon;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
class OrderController {

    private final OrderService orderService;

    @PostMapping( "/create")
    void addOrder(@RequestBody CreateOrderCommon createOrderCommon) throws JsonProcessingException {
            orderService.addOrder(createOrderCommon);
    }
}
