package com.notification.service;

import com.notification.model.InventoryReservedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class NotificationConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(
            topics = "inventory.reserved",
            groupId = "notification-service"
    )
    public void consume(String message) {

        InventoryReservedEvent event = objectMapper.readValue(message, InventoryReservedEvent.class);

        System.out.printf(
                "NOTIFICATION -> orderId=%s, productId=%s, quantity=%d, status=%s%n",
                event.orderId(),
                event.productId(),
                event.quantity(),
                event.status()
        );
    }
}
