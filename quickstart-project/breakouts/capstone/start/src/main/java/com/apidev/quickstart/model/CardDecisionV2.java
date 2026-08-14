package com.apidev.quickstart.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CardDecisionV2(
    UUID decisionId,
    String decision,
    BigDecimal approvedLimit,
    List<String> reasons
) {
}
