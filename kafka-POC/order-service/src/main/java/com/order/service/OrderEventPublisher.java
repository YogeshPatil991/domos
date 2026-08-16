package com.order.service;

import com.order.config.KafkaTopicConfig;
import com.order.model.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class OrderEventPublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void publish(OrderCreatedEvent event) {

        String json = objectMapper.writeValueAsString(event);

        // send message to inventory service
        kafkaTemplate.send(KafkaTopicConfig.ORDER_CREATED, event.orderId(), json);
    }

}
