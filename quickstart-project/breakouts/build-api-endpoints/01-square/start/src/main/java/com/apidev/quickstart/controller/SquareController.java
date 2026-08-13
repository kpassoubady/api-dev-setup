package com.apidev.quickstart.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SquareController {

    @GetMapping("/api/v1/math/square")
    public Map<String, Integer> square(@RequestParam int number) {
        // TODO: Return JSON containing the input number and its square.
        return Map.of(
            "input", number,
            "square", 0
        );
    }
}
