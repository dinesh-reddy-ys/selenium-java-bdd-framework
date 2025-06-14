package utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

/**
 * This class represents a client for communicating with OpenAI's GPT API.
 * It sends a request containing a prompt to the GPT model and retrieves the response.
 */
public class GPTClient {

    // API key for authenticating with OpenAI's GPT API
    private static final String API_KEY = "";


    // Base URL for the GPT API endpoint
    private static final String BASE_URL = "https://api.openai.com/v1/chat/completions";

    /**
     * Sends a prompt to the GPT model and returns the response as a string.
     *
     * @param prompt The user's input or query to be sent to the GPT model.
     * @return Response from GPT API as a string.
     * @throws Exception If any error occurs during the request/response cycle.
     */
    public static String ask(String prompt) throws Exception {
        // Load API key from properties file
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            prop.load(fis);
            String apiKey = prop.getProperty("OPENAI_API_KEY");
            // Use apiKey in your code
        }

        // Create a new HTTP client instance for sending the request
        HttpClient client = HttpClient.newHttpClient();

        // Create a message object with user role and content (prompt)
        JSONObject message = new JSONObject();
        message.put("role", "user");
        message.put("content", prompt);

        // Wrap the message inside a JSON array (as required by the API)
        JSONArray messages = new JSONArray().put(message);

        // Build the request body containing the model information and message(s)
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", "gpt-3.5-turbo"); // Specifies the GPT-4 model to be used
        requestBody.put("messages", messages); // Attaches the array of messages

        // Build the HTTP POST request with headers and request body
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL)) // Sets the endpoint URL
                .header("Content-Type", "application/json") // Indicates JSON payload
                .header("Authorization", "Bearer " + API_KEY) // Authorization key
                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString())) // Adds request payload
                .build();

        // Send the HTTP request and receive the response
        HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Check HTTP response status code
        if (response.statusCode() != 200) {
            throw new Exception("Error: HTTP response status code " + response.statusCode());
        }

        // Check if the response body is empty
        if (response.body().isEmpty()) {
            throw new Exception("Error: Empty response body");
        }
        // Parse the response JSON
        JSONObject responseBody = new JSONObject(response.body());

        // Validate API response structure
        if (!responseBody.has("choices")) {
            throw new JSONException("The response JSON does not contain the key 'choices':" + responseBody.toString());
        }

        JSONArray choices = responseBody.getJSONArray("choices");
        if (choices.isEmpty()) {
            throw new JSONException("The response JSON does not contain any choices:" + responseBody.toString());
        }
        // Return the content from the first choice
        JSONObject result = new JSONObject(response.body());
        return choices // Array of choices provided by the model
                .getJSONObject(0) // First choice in the array
                .getJSONObject("message") // Message object in the choice
                .getString("content") // Extracts the message content string
                .trim(); // Removes leading or trailing whitespace
    }
}