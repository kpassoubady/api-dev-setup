package com.apidev.quickstart.service;

import com.apidev.quickstart.model.CardApplicationRequest;
import com.apidev.quickstart.model.CardDecisionV2;
import com.apidev.quickstart.model.CardDecisionV3;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CardDecisionService {

    public Map<String, Object> decideV1(CardApplicationRequest request) {
        boolean eligible = request.creditScore() >= 660;
        BigDecimal approvedLimit = eligible ? cappedLimit(request) : BigDecimal.ZERO;
        return Map.of("eligible", eligible, "approvedLimit", approvedLimit);
    }

    public CardDecisionV2 decideV2(CardApplicationRequest request) {
        CardDecisionV3 decision = decideV3(request);
        return new CardDecisionV2(
            decision.decisionId(),
            decision.decision(),
            decision.approvedLimit(),
            decision.reasons()
        );
    }

    public CardDecisionV3 decideV3(CardApplicationRequest request) {
        BigDecimal ratio = debtToIncomeRatio(request);
        List<String> reasons = new ArrayList<>();
        String decision;

        if (request.creditScore() < 620 || ratio.compareTo(new BigDecimal("0.45")) > 0) {
            decision = "DECLINED";
            if (request.creditScore() < 620) reasons.add("CREDIT_SCORE_BELOW_MINIMUM");
            if (ratio.compareTo(new BigDecimal("0.45")) > 0) reasons.add("DEBT_TO_INCOME_TOO_HIGH");
        } else if (request.creditScore() < 680 || ratio.compareTo(new BigDecimal("0.36")) > 0) {
            decision = "REVIEW";
            reasons.add("MANUAL_UNDERWRITING_REQUIRED");
        } else {
            decision = "APPROVED";
            reasons.add("POLICY_CRITERIA_MET");
        }

        BigDecimal approvedLimit = decision.equals("APPROVED") ? cappedLimit(request) : BigDecimal.ZERO;
        return new CardDecisionV3(
            UUID.randomUUID(),
            decision,
            approvedLimit,
            ratio,
            riskTier(request.creditScore()),
            List.copyOf(reasons),
            "v3"
        );
    }

    BigDecimal debtToIncomeRatio(CardApplicationRequest request) {
        BigDecimal monthlyIncome = request.annualIncome().divide(BigDecimal.valueOf(12), 4, RoundingMode.HALF_UP);
        return request.monthlyDebt().divide(monthlyIncome, 2, RoundingMode.HALF_UP);
    }

    private BigDecimal cappedLimit(CardApplicationRequest request) {
        BigDecimal policyLimit = request.annualIncome().multiply(new BigDecimal("0.20"));
        return request.requestedLimit().min(policyLimit).setScale(2, RoundingMode.HALF_UP);
    }

    private String riskTier(int creditScore) {
        if (creditScore >= 740) return "LOW";
        if (creditScore >= 680) return "MODERATE";
        return "HIGH";
    }
}
