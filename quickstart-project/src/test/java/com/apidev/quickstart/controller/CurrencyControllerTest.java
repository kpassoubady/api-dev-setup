package com.apidev.quickstart.controller;

import static org.hamcrest.Matchers.greaterThan;
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
class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void convertsUsdAmountToInrUsingCurrentRate() throws Exception {
        mockMvc.perform(get("/api/v1/currency/usd-to-inr").param("amount", "100"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.from").value("USD"))
            .andExpect(jsonPath("$.to").value("INR"))
            .andExpect(jsonPath("$.amount").value(100.0))
            .andExpect(jsonPath("$.rate", greaterThan(0.0)))
            .andExpect(jsonPath("$.convertedAmount", greaterThan(0.0)));
    }

    @Test
    void convertingZeroAmountAlwaysReturnsZeroRegardlessOfRate() throws Exception {
        mockMvc.perform(get("/api/v1/currency/usd-to-inr").param("amount", "0"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.convertedAmount").value(0.0));
    }
}
