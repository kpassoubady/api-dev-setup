package com.apidev.quickstart.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderLineItem(
        @NotBlank String sku,
        @NotNull @DecimalMin("0.01") BigDecimal unitPrice,
        @Min(1) int quantity) {
}
