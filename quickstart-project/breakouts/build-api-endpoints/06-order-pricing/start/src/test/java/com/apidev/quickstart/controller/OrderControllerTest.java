package com.apidev.quickstart.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void orderCalculatesSubtotalDiscountTaxAndTotal() throws Exception {
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "items": [
                        {"sku":"BOOK-1","unitPrice":20.00,"quantity":2},
                        {"sku":"PEN-1","unitPrice":5.00,"quantity":8}
                      ]
                    }
                    """))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.subtotal").value(80.0))
            .andExpect(jsonPath("$.totalQuantity").value(10))
            .andExpect(jsonPath("$.quantityDiscount").value(8.0))
            .andExpect(jsonPath("$.tax").value(5.76))
            .andExpect(jsonPath("$.total").value(77.76));
    }

    @Test
    void invalidOrderReturnsBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {"items":[]}
                    """))
            .andExpect(status().isBadRequest());
    }
}
