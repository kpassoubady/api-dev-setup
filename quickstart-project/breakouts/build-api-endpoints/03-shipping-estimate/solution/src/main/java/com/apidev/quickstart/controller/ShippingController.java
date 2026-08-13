package com.apidev.quickstart.controller;

import com.apidev.quickstart.service.ShippingCostService;
import com.apidev.quickstart.service.ShippingCostService.ShippingEstimate;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShippingController {

    private final ShippingCostService shippingCostService;

    public ShippingController(ShippingCostService shippingCostService) {
        this.shippingCostService = shippingCostService;
    }

    @GetMapping("/api/v1/shipping/estimate")
    public Map<String, Object> estimate(
            @RequestParam double weightKg,
            @RequestParam String priority) {
        ShippingEstimate estimate = shippingCostService.estimate(weightKg, priority);
        return Map.of(
            "weightKg", weightKg,
            "priority", priority,
            "baseCost", estimate.baseCost(),
            "priorityFee", estimate.priorityFee(),
            "totalCost", estimate.totalCost()
        );
    }
}
