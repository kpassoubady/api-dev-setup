package com.apidev.quickstart.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CurrencyConversionServiceTest {

    @Test
    void rateStaysWithinPlausibleBoundsOnFirstCall() {
        CurrencyConversionService service = new CurrencyConversionService();

        double rate = service.currentUsdToInrRate();

        assertThat(rate).isBetween(80.0, 86.0);
    }

    @Test
    void rateTrendsUpwardAsCallsAccumulate() {
        CurrencyConversionService service = new CurrencyConversionService();

        double firstRate = service.currentUsdToInrRate();
        double lastRate = firstRate;
        for (int i = 0; i < 1000; i++) {
            lastRate = service.currentUsdToInrRate();
        }

        assertThat(lastRate).isGreaterThan(firstRate + 5.0);
    }
}
