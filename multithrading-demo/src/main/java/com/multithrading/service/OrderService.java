package com.multithrading.service;

import com.multithrading.dto.OrderResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private ShippingService shippingService;

    @Autowired
    private NotificationService notificationService;

    @Qualifier("orderTaskExecutor")
    @Autowired
    private Executor orderTaskExecutor;

    public OrderResult processOrder() {

        long start = System.currentTimeMillis();

        CompletableFuture<String> payment =
                CompletableFuture.supplyAsync(
                        paymentService::paymentProcess,
                        orderTaskExecutor
                );

        CompletableFuture<String> inventory =
                CompletableFuture.supplyAsync(
                        inventoryService::checkInventory,
                        orderTaskExecutor
                );

        CompletableFuture<String> shipping =
                CompletableFuture.supplyAsync(
                        shippingService::calculateShipping,
                        orderTaskExecutor
                );

        CompletableFuture<String> notification =
                CompletableFuture.supplyAsync(
                        notificationService::sendNotification,
                        orderTaskExecutor
                );

        CompletableFuture.allOf(
                payment,
                inventory,
                shipping,
                notification
        ).join();

        long executionTime =
                System.currentTimeMillis() - start;

        return new OrderResult(
                payment.join(),
                inventory.join(),
                shipping.join(),
                notification.join(),
                executionTime
        );
    }
}
