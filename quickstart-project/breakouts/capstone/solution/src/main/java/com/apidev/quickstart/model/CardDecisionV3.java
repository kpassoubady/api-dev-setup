package com.apidev.quickstart.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CardDecisionV3(
    UUID decisionId,
    String decision,
    BigDecimal approvedLimit,
    BigDecimal debtToIncomeRatio,
    String riskTier,
    List<String> reasons,
    String apiVersion
) {
}
