package com.apidev.quickstart.controller;

import com.apidev.quickstart.model.CardApplicationRequest;
import com.apidev.quickstart.model.CardDecisionV2;
import com.apidev.quickstart.model.CardDecisionV3;
import com.apidev.quickstart.service.CardDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Card applications")
@SecurityRequirement(name = "basicAuth")
public class CardApplicationController {

    private final CardDecisionService fService;

    public CardApplicationController(CardDecisionService service) {
        fService = service;
    }

    @PostMapping("/api/v1/card-applications/decisions")
    @Operation(summary = "Get a legacy eligibility decision", deprecated = true)
    public ResponseEntity<Map<String, Object>> decideV1(@Valid @RequestBody CardApplicationRequest request) {
        // TODO 5: Add Deprecation, Sunset, and successor-version Link headers without removing v1.
        return ResponseEntity.ok(fService.decideV1(request));
    }

    @PostMapping("/api/v2/card-applications/decisions")
    @Operation(summary = "Get a traceable card application decision")
    public CardDecisionV2 decideV2(@Valid @RequestBody CardApplicationRequest request) {
        return fService.decideV2(request);
    }

    @PostMapping("/api/v3/card-applications/decisions")
    @Operation(summary = "Get a detailed underwriting decision")
    public CardDecisionV3 decideV3(@Valid @RequestBody CardApplicationRequest request) {
        return fService.decideV3(request);
    }
}
