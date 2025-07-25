package com.generative.ai.controller;

import com.generative.ai.service.GenerativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/generate")
public class GenerativeController {

    private final GenerativeService generativeService;

    @Autowired
    public GenerativeController(GenerativeService generativeService) {
        this.generativeService = generativeService;
    }

    @GetMapping("/{text}")
    public ResponseEntity<String> generateText(@PathVariable("text") String prompt) {
        String response = generativeService.generateText(prompt);
        return ResponseEntity.ok(response);
    }
}
