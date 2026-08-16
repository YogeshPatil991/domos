package com.order.controller;

import com.order.model.CreateOrderRequest;
import com.order.model.OrderCreatedEvent;
import com.order.service.OrderEventPublisher;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderEventPublisher publisher;


    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        String orderId = UUID.randomUUID().toString();

        OrderCreatedEvent event = new OrderCreatedEvent(orderId, request.customerId(), request.productId(), request.quantity(), Instant.now());

        publisher.publish(event);

        return ResponseEntity.accepted().body(Map.of("orderId", orderId, "status", "EVENT_PUBLISHED"
        ));
    }
}
