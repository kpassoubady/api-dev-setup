package com.apidev.quickstart.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquareController {

    @GetMapping("/api/v1/math/square")
    public Map<String, Integer> square(@RequestParam int number) {
        return Map.of(
            "input", number,
            "square", number * number
        );
    }
}
