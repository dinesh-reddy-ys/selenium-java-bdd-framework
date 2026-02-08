package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

/**
 * Lightweight HTTP client for communicating with a locally-hosted Ollama LLM
 * service. This helper wraps request serialization, network configuration, and
 * response parsing so the rest of the test suite can call simple helpers like
 * {@link #askLLM(String)} or {@link #generateTestData(String)}.
 *
 * Design notes:
 * - Uses a shared OkHttpClient with increased timeouts to accommodate LLM
 *   generation latency.
 * - Uses Jackson's ObjectMapper for simple JSON serialization/parsing.
 * - Keeps the API minimal and throws or returns sentinel values on failure to
 *   make test code resilient to service outages.
 */
public class OllamaClient {

	// Base URL for the local Ollama REST API endpoint. Change this only if the
	// service is hosted on a different port or path.
	private static final String URL = "http://localhost:11434/api/generate";

	// Shared OkHttpClient instance configured with generous timeouts because
	// language model generation can take longer than typical HTTP calls.
	private static final OkHttpClient client = new OkHttpClient.Builder()
			.connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS)
			.readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
			.writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS).build();

	// Jackson mapper used for converting between Java maps and JSON strings.
	private static final ObjectMapper mapper = new ObjectMapper();

	// Media type for JSON requests
	private static final MediaType JSON = MediaType.get("application/json");

	/**
	 * Sends the supplied prompt to the Ollama endpoint and returns the model's
	 * response as a plain string.
	 *
	 * Behavior notes:
	 * - On successful network call and JSON parsing, this returns the
	 *   'response' field from the returned JSON map.
	 * - On non-successful HTTP responses, an IOException is thrown containing
	 *   the raw response (caller should handle or fail the test accordingly).
	 *
	 * @param prompt the prompt text to send to the LLM
	 * @return the LLM's text response (trim as needed by callers)
	 * @throws IOException if the HTTP call fails or an unexpected response is received
	 */
	public static String askLLM(String prompt) throws IOException {

		// Build the JSON payload expected by the Ollama API. Using a Map keeps
		// the code easy to read and avoids creating dedicated DTO classes for
		// this small use-case.
		Map<String, Object> payload = new HashMap<>();
		payload.put("model", "llama3");
		payload.put("prompt", prompt);
		payload.put("stream", false);

		String json = mapper.writeValueAsString(payload);

		RequestBody body = RequestBody.create(json, JSON);

		Request request = new Request.Builder().url(URL).post(body).build();

		try (Response response = client.newCall(request).execute()) {

			// Treat any non-successful HTTP response as an I/O failure so tests
			// can decide how to proceed (retry, fail fast, etc.).
			if (!response.isSuccessful()) {
				throw new IOException(response.toString());
			}

			String rawJson = response.body().string();

			// Parse JSON and extract only response text. The Ollama API returns a
			// JSON object where the generated text is available under key
			// 'response' (this code assumes that contract).
			Map<?, ?> result = mapper.readValue(rawJson, Map.class);

			return result.get("response").toString();
		}
	}

	/**
	 * Helper that generates a concise failure analysis using the LLM. It builds
	 * a short diagnostic prompt and returns the model's response. If Ollama is
	 * unavailable or an exception occurs, this method returns a readable
	 * fallback string instead of throwing.
	 *
	 * @param scenarioName the name of the failing test scenario
	 * @param error the error/stacktrace text to analyze
	 * @return short failure analysis or an error message indicating analysis failed
	 */
	public static String analyzeFailure(String scenarioName, String error) {

		try {
			String prompt = "You are a Selenium automation expert.\n" + "Give a SHORT failure analysis.\n\n"
				+ "Format strictly as:\n" + "Root Cause:\n" + "Fix Suggestion:\n\n" + "Scenario: " + scenarioName
				+ "\n" + "Error: " + error;

			return askLLM(prompt);

		} catch (Exception e) {
			// On failure, return a short diagnostic string so callers can still
			// include a readable message in reports or logs.
			return "Ollama analysis failed: " + e.getMessage();
		}
	}

	/**
	 * Helper that generates test-data based on a short instruction. This method
	 * wraps the LLM call and returns a sentinel string on failure so tests can
	 * decide how to proceed (for example, skip data generation on CI if
	 * Ollama isn't available).
	 *
	 * @param instruction short instruction describing the desired test data
	 * @return generated test data as plain text, or "DATA_GEN_FAILED" if the LLM call failed
	 */

	public static String generateTestData(String instruction) {
		try {
			String prompt = "Generate test data only." + "Return plain text without explanation.\n" + instruction;
			return askLLM(prompt).trim();
		} catch (Exception e) {
			// Return sentinel value instead of throwing to keep test flows simple.
			return "DATA_GEN_FAILED";
		}
	}
}