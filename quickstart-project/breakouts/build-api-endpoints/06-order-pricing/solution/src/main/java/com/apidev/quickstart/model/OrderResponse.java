package com.apidev.quickstart.model;

import java.math.BigDecimal;

public record OrderResponse(
        BigDecimal subtotal,
        int totalQuantity,
        BigDecimal quantityDiscount,
        BigDecimal tax,
        BigDecimal total) {
}
