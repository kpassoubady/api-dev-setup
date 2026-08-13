package com.apidev.quickstart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ShippingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void standardEstimateUsesWeightBasedCost() throws Exception {
        mockMvc.perform(get("/api/v1/shipping/estimate")
                .param("weightKg", "2")
                .param("priority", "standard"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.weightKg").value(2.0))
            .andExpect(jsonPath("$.priority").value("standard"))
            .andExpect(jsonPath("$.baseCost").value(5.0))
            .andExpect(jsonPath("$.priorityFee").value(0.0))
            .andExpect(jsonPath("$.totalCost").value(5.0));
    }

    @Test
    void expressEstimateAddsDeterministicFee() throws Exception {
        mockMvc.perform(get("/api/v1/shipping/estimate")
                .param("weightKg", "2")
                .param("priority", "express"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.baseCost").value(5.0))
            .andExpect(jsonPath("$.priorityFee").value(7.5))
            .andExpect(jsonPath("$.totalCost").value(12.5));
    }
}
