package com.generative.ai.service;

import com.generative.ai.handler.GenerativeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenerativeService {

    private final GenerativeHandler generativeHandler;

    @Autowired
    public GenerativeService(GenerativeHandler generativeHandler) {
         this.generativeHandler = generativeHandler;
    }

    public String generateText(String prompt) {
        return generativeHandler.requestPromptToGenerate(prompt);
    }

}
