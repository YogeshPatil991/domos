package com.multithrading.controller;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.OrderResult;
import com.multithrading.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/process/sequential")
    public ResponseEntity<?> processSequential(@Valid @RequestBody OrderRequest request) {

        OrderResult orderResult = orderService.processSequential(request);
        return ResponseEntity.ok().body(orderResult);
    }

    @PostMapping("/process/parallel")
    public ResponseEntity<?> processParallel(@Valid @RequestBody OrderRequest request) {

        OrderResult orderResult = orderService.processParallel(request);
        return ResponseEntity.ok().body(orderResult);
    }
}
