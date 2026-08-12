package com.multithrading.service;

import com.multithrading.dto.OrderRequest;
import com.multithrading.dto.TaskResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {

    @Autowired
    private TaskService taskService;

    public TaskResult notification(OrderRequest request) {

        return taskService.execute("NOTIFICATION", 2000, () -> "Notification sent to " + request.customerId());
    }

}
