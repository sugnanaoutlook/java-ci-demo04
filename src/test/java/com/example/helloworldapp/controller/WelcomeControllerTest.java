package com.example.helloworldapp.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WelcomeController.class)
public class WelcomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Welcome to Spring Boot Application!"));
    }

    @Test
    public void testWelcome() throws Exception {
        mockMvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to our Hello World Spring Boot Application!"));
    }

    @Test
    public void testWelcomeWithName() throws Exception {
        mockMvc.perform(get("/welcome/John"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello John! Welcome to our Spring Boot Application!"));
    }

    @Test
    public void testGreetWithParam() throws Exception {
        mockMvc.perform(get("/greet?name=Alice"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello Alice! Greetings from Spring Boot!"));
    }

    @Test
    public void testGreetWithoutParam() throws Exception {
        mockMvc.perform(get("/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World! Greetings from Spring Boot!"));
    }

    @Test
    public void testHealth() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Application is running successfully!"));
    }
}
