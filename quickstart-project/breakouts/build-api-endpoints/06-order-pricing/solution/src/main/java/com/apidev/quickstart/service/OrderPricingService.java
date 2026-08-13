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
        BigDecimal subtotal = request.items().stream()
            .map(this::lineSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        int totalQuantity = request.items().stream()
            .mapToInt(OrderLineItem::quantity)
            .sum();
        BigDecimal quantityDiscount = totalQuantity >= DISCOUNT_QUANTITY
            ? subtotal.multiply(DISCOUNT_RATE)
            : BigDecimal.ZERO;
        BigDecimal discountedSubtotal = subtotal.subtract(quantityDiscount);
        BigDecimal tax = discountedSubtotal.multiply(TAX_RATE);
        BigDecimal total = discountedSubtotal.add(tax);

        return new OrderResponse(
            money(subtotal),
            totalQuantity,
            money(quantityDiscount),
            money(tax),
            money(total)
        );
    }

    private BigDecimal lineSubtotal(OrderLineItem item) {
        return item.unitPrice().multiply(BigDecimal.valueOf(item.quantity()));
    }

    private BigDecimal money(BigDecimal amount) {
        return amount.setScale(2, RoundingMode.HALF_UP);
    }
}
