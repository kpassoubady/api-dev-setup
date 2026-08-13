package com.apidev.quickstart.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TextDetailsController {

    @GetMapping("/api/v1/text/{word}/details")
    public Map<String, Object> details(@PathVariable String word) {
        return Map.of(
            "original", word,
            "length", word.length(),
            "uppercase", word.toUpperCase()
        );
    }
}
