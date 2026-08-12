package com.multithrading.controller;

import com.multithrading.dto.OrderResult;
import com.multithrading.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/process")
    public OrderResult processOrder() {
        return orderService.processOrder();
    }
}
