package com.apidev.quickstart.service;

import org.springframework.stereotype.Service;

@Service
public class ShippingCostService {

    private static final double COST_PER_KILOGRAM = 2.50;
    private static final double EXPRESS_FEE = 7.50;

    public ShippingEstimate estimate(double weightKg, String priority) {
        // TODO: Calculate base cost from weight and add the fee for the selected priority.
        return new ShippingEstimate(0.0, 0.0, 0.0);
    }

    public record ShippingEstimate(double baseCost, double priorityFee, double totalCost) {
    }
}
