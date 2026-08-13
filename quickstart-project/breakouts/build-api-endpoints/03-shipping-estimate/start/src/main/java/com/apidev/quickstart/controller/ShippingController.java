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
        // TODO: Call ShippingCostService and return all estimate inputs and calculated fields.
        return Map.of(
            "weightKg", weightKg,
            "priority", priority,
            "baseCost", 0.0,
            "priorityFee", 0.0,
            "totalCost", 0.0
        );
    }
}
