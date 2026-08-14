package com.apidev.quickstart.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record CardApplicationRequest(
    @NotNull @DecimalMin("1.00") BigDecimal annualIncome,
    @NotNull @DecimalMin("0.00") BigDecimal monthlyDebt,
    @Min(300) @Max(850) int creditScore,
    @NotNull @DecimalMin("500.00") BigDecimal requestedLimit
) {
}
