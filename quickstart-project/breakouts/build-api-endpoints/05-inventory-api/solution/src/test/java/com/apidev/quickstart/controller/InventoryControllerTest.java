package com.apidev.quickstart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void collectionReturnsSeededInventory() throws Exception {
        mockMvc.perform(get("/api/v1/inventory"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].sku").value("BOOK-1"))
            .andExpect(jsonPath("$[0].name").value("API Fundamentals"))
            .andExpect(jsonPath("$[0].quantity").value(8));
    }

    @Test
    void itemLookupReturnsItemOrNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/inventory/BOOK-1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.sku").value("BOOK-1"));

        mockMvc.perform(get("/api/v1/inventory/UNKNOWN"))
            .andExpect(status().isNotFound());
    }

    @Test
    void createReturnsCreatedAndLocation() throws Exception {
        mockMvc.perform(post("/api/v1/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"sku":"PEN-1","name":"API Pen","quantity":20}
                    """))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/api/v1/inventory/PEN-1"))
            .andExpect(jsonPath("$.sku").value("PEN-1"));
    }

    @Test
    void duplicateSkuReturnsConflict() throws Exception {
        mockMvc.perform(post("/api/v1/inventory")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"sku":"BOOK-1","name":"Duplicate","quantity":1}
                    """))
            .andExpect(status().isConflict());
    }
}
