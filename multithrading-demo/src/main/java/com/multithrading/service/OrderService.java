package com.multithrading.service;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.OrderResult;
import com.multithrading.dto.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ShippingService shippingService;

    @Qualifier("orderTaskExecutor")
    @Autowired
    private Executor orderExecutor;

    public OrderResult processParallel(
            OrderRequest request) {

        long start = System.currentTimeMillis();

        CompletableFuture<TaskResult> payment = CompletableFuture.supplyAsync(() -> paymentService.payment(request), orderExecutor);

        CompletableFuture<TaskResult> inventory = CompletableFuture.supplyAsync(() -> inventoryService.inventory(request), orderExecutor);

        CompletableFuture<TaskResult> shipping = CompletableFuture.supplyAsync(() -> shippingService.shipping(request), orderExecutor);

        CompletableFuture<TaskResult> notification = CompletableFuture.supplyAsync(() -> notificationService.notification(request), orderExecutor);

        CompletableFuture.allOf(payment, inventory, shipping, notification).join();

        long executionTime = System.currentTimeMillis() - start;

        List<TaskResult> tasks = List.of(payment.join(), inventory.join(), shipping.join(), notification.join()
        );

        return new OrderResult(request.orderId(), "PARALLEL", "SUCCESS", executionTime, tasks);
    }

    public OrderResult processSequential(OrderRequest request) {

        long start = System.currentTimeMillis();

        TaskResult payment = paymentService.payment(request);

        TaskResult inventory = inventoryService.inventory(request);

        TaskResult shipping = shippingService.shipping(request);

        TaskResult notification = notificationService.notification(request);

        long executionTime = System.currentTimeMillis() - start;

        return new OrderResult(request.orderId(), "SEQUENTIAL", "SUCCESS", executionTime, List.of(payment, inventory, shipping, notification)
        );
    }
}
