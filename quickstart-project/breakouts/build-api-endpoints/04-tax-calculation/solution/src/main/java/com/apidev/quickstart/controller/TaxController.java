package com.apidev.quickstart.controller;

import com.apidev.quickstart.service.TaxCalculationService;
import com.apidev.quickstart.service.TaxCalculationService.TaxCalculation;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxController {

    private final TaxCalculationService taxCalculationService;

    public TaxController(TaxCalculationService taxCalculationService) {
        this.taxCalculationService = taxCalculationService;
    }

    @GetMapping("/api/v1/tax/calculate")
    public Map<String, Object> calculate(
            @RequestParam double subtotal,
            @RequestParam String region) {
        TaxCalculation calculation = taxCalculationService.calculate(subtotal, region);
        return Map.of(
            "subtotal", subtotal,
            "region", region,
            "rate", calculation.rate(),
            "tax", calculation.tax(),
            "total", calculation.total()
        );
    }
}
