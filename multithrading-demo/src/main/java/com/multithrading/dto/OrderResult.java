package com.multithrading.dto;

import java.util.List;

public record OrderResult(String orderId,
                          String executionMode,
                          String status,
                          long executionTimeMs,
                          List<TaskResult> tasks) {


}