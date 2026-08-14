package com.apidev.quickstart.controller;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.apidev.quickstart.security.RateLimitFilter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = {
    "capstone.member.password=member-pass",
    "capstone.underwriter.password=underwriter-pass"
})
@AutoConfigureMockMvc
class CardApplicationControllerTest {

    private static final String VALID_APPLICATION = """
        {"annualIncome":120000,"monthlyDebt":2000,"creditScore":720,"requestedLimit":10000}
        """;

    @Autowired
    private MockMvc fMockMvc;

    @Autowired
    private RateLimitFilter fRateLimitFilter;

    @BeforeEach
    void resetRateLimits() {
        fRateLimitFilter.reset();
    }

    @Test
    void v1RemainsAvailableWithRetirementHeaders() throws Exception {
        fMockMvc.perform(post("/api/v1/card-applications/decisions")
                .with(httpBasic("member", "member-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isOk())
            .andExpect(header().string("Deprecation", "@1793491200"))
            .andExpect(header().string("Sunset", "Thu, 31 Dec 2026 23:59:59 GMT"))
            .andExpect(header().string("Link", "</api/v2/card-applications/decisions>; rel=\"successor-version\""))
            .andExpect(jsonPath("$.eligible").value(true));
    }

    @Test
    void v2AddsATraceableDecisionContract() throws Exception {
        fMockMvc.perform(post("/api/v2/card-applications/decisions")
                .with(httpBasic("member", "member-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.decisionId").isNotEmpty())
            .andExpect(jsonPath("$.decision").value("APPROVED"))
            .andExpect(jsonPath("$.approvedLimit").value(10000.0))
            .andExpect(jsonPath("$.reasons[0]").value("POLICY_CRITERIA_MET"));
    }

    @Test
    void v3AddsUnderwritingDetails() throws Exception {
        fMockMvc.perform(post("/api/v3/card-applications/decisions")
                .with(httpBasic("underwriter", "underwriter-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.debtToIncomeRatio").value(0.2))
            .andExpect(jsonPath("$.riskTier").value("MODERATE"))
            .andExpect(jsonPath("$.apiVersion").value("v3"));
    }

    @Test
    void missingCredentialsReturn401() throws Exception {
        fMockMvc.perform(post("/api/v2/card-applications/decisions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isUnauthorized());
    }

    @Test
    void memberCannotCallUnderwriterOnlyV3() throws Exception {
        fMockMvc.perform(post("/api/v3/card-applications/decisions")
                .with(httpBasic("member", "member-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isForbidden());
    }

    @Test
    void invalidCreditScoreReturns400() throws Exception {
        String invalidApplication = """
            {"annualIncome":120000,"monthlyDebt":2000,"creditScore":900,"requestedLimit":10000}
            """;

        fMockMvc.perform(post("/api/v2/card-applications/decisions")
                .with(httpBasic("member", "member-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidApplication))
            .andExpect(status().isBadRequest());
    }

    @Test
    void fourthAuthorizedRequestReturns429() throws Exception {
        for (int request = 1; request <= 3; request++) {
            fMockMvc.perform(post("/api/v2/card-applications/decisions")
                    .with(httpBasic("member", "member-pass"))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(VALID_APPLICATION))
                .andExpect(status().isOk())
                .andExpect(header().string("RateLimit-Limit", "3"));
        }

        fMockMvc.perform(post("/api/v2/card-applications/decisions")
                .with(httpBasic("member", "member-pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(VALID_APPLICATION))
            .andExpect(status().isTooManyRequests())
            .andExpect(header().string("Retry-After", "60"))
            .andExpect(jsonPath("$.error").value("rate_limit_exceeded"));
    }
}
