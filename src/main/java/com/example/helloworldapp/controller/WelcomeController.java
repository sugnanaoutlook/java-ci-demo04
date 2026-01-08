package com.example.helloworldapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String home() {
        return "Hello World! Welcome to Spring Boot Application!";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to our Hello World Spring Boot Application!";
    }

    @GetMapping("/welcome/{name}")
    public String welcomeWithName(@PathVariable String name) {
        return "Hello " + name + "! Welcome to our Spring Boot Application!";
    }

    @GetMapping("/greet")
    public String greetWithParam(@RequestParam(defaultValue = "World") String name) {
        return "Hello " + name + "! Greetings from Spring Boot!";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
}
