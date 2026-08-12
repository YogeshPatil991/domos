package com.multithrading.dto;

public record TaskResult(String task,
                         String status,
                         String thread,
                         long executionTimeMs,
                         String message) {
}
