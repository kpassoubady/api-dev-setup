package com.apidev.quickstart.service;

import org.springframework.stereotype.Service;

@Service
public class TaxCalculationService {

    public TaxCalculation calculate(double subtotal, String region) {
        // TODO: Select the region rate, then calculate tax and total.
        return new TaxCalculation(0.0, 0.0, 0.0);
    }

    public record TaxCalculation(double rate, double tax, double total) {
    }
}
