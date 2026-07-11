package com.apidev.quickstart.controller;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/api/v1/greetings/{name}")
    public Map<String, String> greet(@PathVariable String name) {
        return Map.of("message", "Hello, " + name + "! Your API dev setup is working.");
    }
}
