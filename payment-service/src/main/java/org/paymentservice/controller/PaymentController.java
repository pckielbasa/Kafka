package org.paymentservice.controller;

import lombok.RequiredArgsConstructor;
import org.paymentservice.model.Payment;
import org.springframework.web.bind.annotation.*;
import org.paymentservice.service.PaymentService;

@RestController
@RequestMapping("payment")
@RequiredArgsConstructor
class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/add")
    public void createPayment(@RequestBody Payment payment) {
        paymentService.createPayment(payment);
    }
}
