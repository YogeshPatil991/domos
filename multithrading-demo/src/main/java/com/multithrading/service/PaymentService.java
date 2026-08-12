package com.multithrading.service;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private TaskService taskService;

    public TaskResult payment(OrderRequest request) {

        return taskService.execute("PAYMENT", 2000, () -> "Payment successful for amount " + request.amount());
    }
}
