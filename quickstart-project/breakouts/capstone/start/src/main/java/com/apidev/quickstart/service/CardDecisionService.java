package com.apidev.quickstart.service;

import com.apidev.quickstart.model.CardApplicationRequest;
import com.apidev.quickstart.model.CardDecisionV2;
import com.apidev.quickstart.model.CardDecisionV3;
import java.math.BigDecimal;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CardDecisionService {

    public Map<String, Object> decideV1(CardApplicationRequest request) {
        // TODO 1: Return eligible and approvedLimit using the acceptance criteria in the lab guide.
        return Map.of();
    }

    public CardDecisionV2 decideV2(CardApplicationRequest request) {
        // TODO 2: Return a decision ID, decision, approved limit, and reason list.
        throw new UnsupportedOperationException("TODO 2");
    }

    public CardDecisionV3 decideV3(CardApplicationRequest request) {
        // TODO 3: Add debt-to-income ratio, risk tier, reasons, and apiVersion v3.
        throw new UnsupportedOperationException("TODO 3");
    }

    BigDecimal debtToIncomeRatio(CardApplicationRequest request) {
        // TODO 4: Divide monthly debt by monthly income and round to two decimal places.
        return BigDecimal.ZERO;
    }
}
