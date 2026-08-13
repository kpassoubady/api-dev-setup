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
class TextDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void detailsReturnsTextTransformations() throws Exception {
        mockMvc.perform(get("/api/v1/text/spring/details"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.original").value("spring"))
            .andExpect(jsonPath("$.length").value(6))
            .andExpect(jsonPath("$.uppercase").value("SPRING"));
    }
}
