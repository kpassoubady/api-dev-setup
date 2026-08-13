package com.apidev.quickstart.service;

import com.apidev.quickstart.model.OrderLineItem;
import com.apidev.quickstart.model.OrderRequest;
import com.apidev.quickstart.model.OrderResponse;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Service;

@Service
public class OrderPricingService {

    private static final int DISCOUNT_QUANTITY = 10;
    private static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");
    private static final BigDecimal TAX_RATE = new BigDecimal("0.08");

    public OrderResponse price(OrderRequest request) {
        // TODO: Calculate subtotal and total quantity across every line item.
        // TODO: Apply a 10% discount at 10+ items, then 8% tax, rounding money to two decimals.
        return new OrderResponse(
            BigDecimal.ZERO,
            0,
            BigDecimal.ZERO,
            BigDecimal.ZERO,
            BigDecimal.ZERO
        );
    }

    private BigDecimal lineSubtotal(OrderLineItem item) {
        // TODO: Multiply a line item's unit price by its quantity.
        return BigDecimal.ZERO;
    }

    private BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
