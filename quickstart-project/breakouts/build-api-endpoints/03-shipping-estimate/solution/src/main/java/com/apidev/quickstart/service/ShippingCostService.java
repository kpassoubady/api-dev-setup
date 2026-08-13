package com.apidev.quickstart.service;

import org.springframework.stereotype.Service;

@Service
public class ShippingCostService {

    private static final double COST_PER_KILOGRAM = 2.50;
    private static final double EXPRESS_FEE = 7.50;

    public ShippingEstimate estimate(double weightKg, String priority) {
        double baseCost = weightKg * COST_PER_KILOGRAM;
        double priorityFee = switch (priority) {
            case "standard" -> 0.0;
            case "express" -> EXPRESS_FEE;
            default -> throw new IllegalArgumentException("priority must be standard or express");
        };
        return new ShippingEstimate(baseCost, priorityFee, baseCost + priorityFee);
    }

    public record ShippingEstimate(double baseCost, double priorityFee, double totalCost) {
    }
}
