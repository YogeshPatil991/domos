package com.multithrading.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderRequest(

        @NotBlank
        String orderId,

        @NotBlank
        String customerId,

        @NotBlank
        String productId,

        @NotNull
        @Positive
        Integer quantity,

        @NotNull
        @Positive
        Double amount

) {
}
