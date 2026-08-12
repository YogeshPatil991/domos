package com.multithrading.service;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InventoryService {

    @Autowired
    private TaskService taskService;

    public TaskResult inventory(OrderRequest request) {

        return taskService.execute("INVENTORY", 2000, () -> "Inventory available for " + request.productId());
    }
}
