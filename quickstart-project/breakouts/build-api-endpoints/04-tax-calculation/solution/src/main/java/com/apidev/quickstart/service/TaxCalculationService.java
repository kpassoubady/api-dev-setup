package com.apidev.quickstart.service;

import org.springframework.stereotype.Service;

@Service
public class TaxCalculationService {

    public TaxCalculation calculate(double subtotal, String region) {
        double rate = switch (region) {
            case "standard" -> 0.20;
            case "reduced" -> 0.10;
            default -> throw new IllegalArgumentException("region must be standard or reduced");
        };
        double tax = subtotal * rate;
        return new TaxCalculation(rate, tax, subtotal + tax);
    }

    public record TaxCalculation(double rate, double tax, double total) {
    }
}
