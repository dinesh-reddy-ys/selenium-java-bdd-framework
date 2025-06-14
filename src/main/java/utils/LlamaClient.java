package utils;

import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * A client application to interact with Ollama's LLaMA model API.
 * This sends a request with a prompt and parses the response JSON.
 */
public class LlamaClient {

    public static void main(String[] args) throws Exception {
        // Create an instance of HttpClient to send HTTP requests
        HttpClient client = HttpClient.newHttpClient();

        // Create a JSON object for the request payload
        JSONObject body = new JSONObject();
        body.put("model", "llama3"); // Specify the model to use for generation
        body.put("prompt", "Write a Java Selenium test for a login page."); // Define the prompt for the model
        body.put("stream",false); // Indicate whether to stream the response or not

        // Build an HTTP POST request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:11434/api/generate")) // Backend API endpoint
                .header("Content-Type", "application/json") // Set Content-Type to JSON
                .timeout(Duration.ofSeconds(10))
                .POST(HttpRequest.BodyPublishers.ofString(body.toString())) // Pass the JSON payload
                .build();

        // Send the HTTP request and capture the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check if the response was successful (status 200)
        if (response.statusCode() == 200) {
            // Parse the response body as a JSON object
            JSONObject responseBody = new JSONObject(response.body());
            
            // Extract specific fields from the response JSON
            String generatedText = responseBody.optString("text", "No text generated");
            boolean success = responseBody.optBoolean("success", false);

            // Print the extracted response details
            System.out.println("Generated Text: " + generatedText);
            System.out.println("Success: " + success);
        } else {
            // Handle non-200 responses (e.g., error cases)
            System.err.println("Failed to get a valid response. HTTP Status: " + response.statusCode());
            System.err.println("Response Body: " + response.body());
        }
    }
}