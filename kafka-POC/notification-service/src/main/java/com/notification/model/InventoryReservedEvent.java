package com.notification.model;

import java.time.Instant;

public record InventoryReservedEvent(
        String orderId,
        String productId,
        int quantity,
        String status,
        Instant reservedAt
) {}
