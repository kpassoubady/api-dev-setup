package com.apidev.quickstart.controller;

import com.apidev.quickstart.service.CurrencyConversionService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyController {

    private final CurrencyConversionService currencyConversionService;

    public CurrencyController(CurrencyConversionService currencyConversionService) {
        this.currencyConversionService = currencyConversionService;
    }

    @GetMapping("/api/v1/currency/usd-to-inr")
    public Map<String, Object> convert(@RequestParam double amount) {
        double rate = currencyConversionService.currentUsdToInrRate();
        return Map.of(
            "from", "USD",
            "to", "INR",
            "amount", amount,
            "rate", rate,
            "convertedAmount", amount * rate
        );
    }
}
