package com.apidev.quickstart.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperatureController {

    @GetMapping("/api/v1/temperature/fahrenheit-to-celsius")
    public Map<String, Object> convert(@RequestParam double fahrenheit) {
        double celsius = (fahrenheit - 32) * 5 / 9;
        return Map.of(
            "fahrenheit", fahrenheit,
            "celsius", celsius
        );
    }
}
