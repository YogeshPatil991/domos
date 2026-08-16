package com.inventory.service;

import com.inventory.config.KafkaConfig;
import com.inventory.model.InventoryReservedEvent;
import com.inventory.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.time.Instant;

@Service
public class InventoryConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaConfig.ORDER_CREATED, groupId = "inventory-service")
    public void consume(String message) {

        //parse string message into OrderCreatedEvent Object
        OrderCreatedEvent event = objectMapper.readValue(message, OrderCreatedEvent.class);

        // Demo business rule: product IDs beginning with FAIL simulate a failure.
        if (event.productId().startsWith("FAIL")) {
            throw new IllegalStateException("Simulated inventory failure for product " + event.productId());
        }

        //Create new object  InventoryReservedEvent
        InventoryReservedEvent reserved = new InventoryReservedEvent(event.orderId(), event.productId(), event.quantity(), "RESERVED", Instant.now());

        String json = objectMapper.writeValueAsString(reserved);
        System.out.println(json);

        //send message to notification service
        kafkaTemplate.send(KafkaConfig.INVENTORY_RESERVED, event.orderId(), json);
    }
}
