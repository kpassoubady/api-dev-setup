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
class TaxControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void standardRegionUsesTwentyPercentRate() throws Exception {
        mockMvc.perform(get("/api/v1/tax/calculate")
                .param("subtotal", "100")
                .param("region", "standard"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.subtotal").value(100.0))
            .andExpect(jsonPath("$.region").value("standard"))
            .andExpect(jsonPath("$.rate").value(0.2))
            .andExpect(jsonPath("$.tax").value(20.0))
            .andExpect(jsonPath("$.total").value(120.0));
    }

    @Test
    void reducedRegionUsesTenPercentRate() throws Exception {
        mockMvc.perform(get("/api/v1/tax/calculate")
                .param("subtotal", "100")
                .param("region", "reduced"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.rate").value(0.1))
            .andExpect(jsonPath("$.tax").value(10.0))
            .andExpect(jsonPath("$.total").value(110.0));
    }
}
