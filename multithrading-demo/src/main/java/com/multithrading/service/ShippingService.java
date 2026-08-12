package com.multithrading.service;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    @Autowired
    private TaskService taskService;

    public TaskResult shipping(OrderRequest request) {

        return taskService.execute("SHIPPING", 2000, () -> "Shipping calculated");
    }

}
