package com.generative.ai.handler;

import com.generative.ai.response.AIResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GenerativeHandler {

    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Value("${together.api.key}")
    private String apiKey;

    /**
     * This method sends a request to the Together API to generate text based on the provided prompt.
     *
     * @param promptRequest The prompt to be sent to the API for text generation.
     * @return The response from the API as a String.
     */
    public String requestPromptToGenerate(String promptRequest) {
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);
        /**
         * Choose the model you want to use from the Together API.
         * temperature and top_p are parameters that control the randomness of the output.
         */
        String requestJson = String.format("""
                {
                    "model": "deepseek-ai/DeepSeek-R1-Distill-Llama-70B-free",
                    "prompt": "%s",
                    "max_tokens": 200,
                    "temperature": 0.7,
                    "top_p": 0.9
                }
                """, promptRequest);

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        AIResponse response = restTemplate.postForObject(
                "https://api.together.xyz/v1/completions",
                entity,
                AIResponse.class
        );
        return response.getChoices().get(0).getText();
    }

}
