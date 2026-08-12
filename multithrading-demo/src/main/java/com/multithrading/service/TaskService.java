package com.multithrading.service;

import com.multithrading.dto.TaskResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    public TaskResult execute(String taskName, long delay, TaskAction action) {

        long start = System.currentTimeMillis();

        String threadName = Thread.currentThread().getName();

        LOGGER.info("{} started | thread={}", taskName, threadName);

        try {

            Thread.sleep(delay);

            String message = action.execute();

            long executionTime = System.currentTimeMillis() - start;

            LOGGER.info("{} completed | thread={} | time={}ms", taskName, threadName, executionTime);

            return new TaskResult(taskName, "SUCCESS", threadName, executionTime, message);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();

            return new TaskResult(taskName, "INTERRUPTED", threadName, System.currentTimeMillis() - start, "Task interrupted");
        }
    }

    @FunctionalInterface
    public interface TaskAction {

        String execute();
    }
}
