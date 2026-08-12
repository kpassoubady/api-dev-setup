package com.apidev.quickstart.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class CurrencyConversionService {

    private static final double BASE_USD_TO_INR_RATE = 83.00;
    private static final double PROGRESSIVE_STEP = 0.01;
    private static final double RANDOM_JITTER_RANGE = 0.50;

    private final AtomicInteger callCount = new AtomicInteger(0);

    public double currentUsdToInrRate() {
        int calls = callCount.incrementAndGet();
        double progressiveRate = BASE_USD_TO_INR_RATE + (calls * PROGRESSIVE_STEP);
        double jitter = ThreadLocalRandom.current().nextDouble(-RANDOM_JITTER_RANGE, RANDOM_JITTER_RANGE);
        return progressiveRate + jitter;
    }
}
