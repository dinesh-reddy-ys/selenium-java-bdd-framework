package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

public class OllamaClient {

    private static final String URL =
            "http://localhost:11434/api/generate";

    private static final OkHttpClient client =
            new OkHttpClient.Builder()
                    .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
                    .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
                    .build();

    private static final ObjectMapper mapper =
            new ObjectMapper();

    private static final MediaType JSON =
            MediaType.get("application/json");

    /**
     * Sends prompt to Ollama and returns response.
     */
    public static String askLLM(String prompt) throws IOException {

        Map<String, Object> payload = new HashMap<>();
        payload.put("model", "llama3");
        payload.put("prompt", prompt);
        payload.put("stream", false);

        String json = mapper.writeValueAsString(payload);

        RequestBody body = RequestBody.create(json, JSON);

        Request request = new Request.Builder()
                .url(URL)
                .post(body)
                .build();

        try (Response response =
                     client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new IOException(response.toString());
            }

            String rawJson = response.body().string();

            // Parse JSON and extract only response text
            Map<?, ?> result =
                    mapper.readValue(rawJson, Map.class);

            return result.get("response").toString();
        }
    }


    /**
     * Failure analyzer helper
     */
    public static String analyzeFailure(
            String scenarioName,
            String error) {

        try {
        	String prompt =
        	        "You are a Selenium automation expert.\n"
        	        + "Give a SHORT failure analysis.\n\n"
        	        + "Format strictly as:\n"
        	        + "Root Cause:\n"
        	        + "Fix Suggestion:\n\n"
        	        + "Scenario: " + scenarioName + "\n"
        	        + "Error: " + error;


            return askLLM(prompt);

        } catch (Exception e) {
            return "Ollama analysis failed: "
                    + e.getMessage();
        }
    }
}
