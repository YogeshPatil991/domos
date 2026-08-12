package com.multithrading.dto;

public class OrderResult {

    private String paymentStatus;
    private String inventoryStatus;
    private String shippingStatus;
    private String notificationStatus;
    private long executionTimeMs;

    public OrderResult(
            String paymentStatus,
            String inventoryStatus,
            String shippingStatus,
            String notificationStatus,
            long executionTimeMs) {

        this.paymentStatus = paymentStatus;
        this.inventoryStatus = inventoryStatus;
        this.shippingStatus = shippingStatus;
        this.notificationStatus = notificationStatus;
        this.executionTimeMs = executionTimeMs;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getInventoryStatus() {
        return inventoryStatus;
    }

    public String getShippingStatus() {
        return shippingStatus;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public long getExecutionTimeMs() {
        return executionTimeMs;
    }
}