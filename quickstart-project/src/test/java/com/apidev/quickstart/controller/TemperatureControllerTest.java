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
class TemperatureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void convertsFahrenheitToCelsius() throws Exception {
        mockMvc.perform(get("/api/v1/temperature/fahrenheit-to-celsius").param("fahrenheit", "212"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.fahrenheit").value(212.0))
            .andExpect(jsonPath("$.celsius").value(100.0));
    }

    @Test
    void convertsFreezingPointToZeroCelsius() throws Exception {
        mockMvc.perform(get("/api/v1/temperature/fahrenheit-to-celsius").param("fahrenheit", "32"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.celsius").value(0.0));
    }
}
