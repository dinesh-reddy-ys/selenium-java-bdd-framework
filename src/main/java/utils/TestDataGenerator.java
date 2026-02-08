package utils;

/**
 * Utility class for generating test data used throughout the test suite.
 *
 * <p>This class centralizes test data generation so tests can request realistic
 * values (names, emails, ages, etc.) without repeating generation logic.
 * Currently the methods delegate to an external helper `OllamaClient.generateTestData`.
 * If needed, this class can be extended later to include local generators
 * or to mock responses for offline unit tests.</p>
 *
 * Usage examples:
 * - TestDataGenerator.randomFirstName() -> returns a plausible first name
 * - TestDataGenerator.randomEmail() -> returns a plausible email address
 *
 * Note: This class intentionally keeps methods static for easy, global use in
 * test code. The delegation to OllamaClient keeps generation logic outside the
 * test codebase and allows swapping implementations later.
 */

public class TestDataGenerator {

	/**
	 * Returns a randomly generated first name.
	 * Delegates to OllamaClient which encapsulates the generation implementation.
	 *
	 * @return random first name as a String
	 */
	public static String randomFirstName() {
		return OllamaClient.generateTestData("Generate a random first name.");
	}

	/**
	 * Returns a randomly generated last name.
	 *
	 * @return random last name as a String
	 */
	public static String randomLastName() {
		return OllamaClient.generateTestData("Generate a random last name.");
	}

	/**
	 * Returns a valid-looking random email address.
	 *
	 * @return random email address as a String
	 */
	public static String randomEmail() {
		return OllamaClient.generateTestData("Generate a random valid email address.");
	}

	/**
	 * Returns a random age (string representation) within a typical adult range.
	 *
	 * @return random age as a String (e.g., "27")
	 */
	public static String randomAge() {
		return OllamaClient.generateTestData("Generate a random age between 18 and 100.");
	}

	/**
	 * Returns a random salary (string representation) within a sensible range.
	 *
	 * @return random salary as a String (e.g., "54000")
	 */
	public static String randomSalary() {
		return OllamaClient.generateTestData("Generate a random salary between 30000 and 150000.");
	}

	/**
	 * Returns a random department name (e.g., "Engineering", "Sales").
	 *
	 * @return random department name as a String
	 */
	public static String randomDepartment() {
		return OllamaClient.generateTestData("Generate a random department name.");
	}

	/**
	 * Returns a random 10-digit mobile number as a string.
	 * Formatting, if required, should be applied by the caller.
	 *
	 * @return random mobile number as a String
	 */
	public static String randomMobileNumber() {
		return OllamaClient.generateTestData("Generate a random 10-digit mobile number.");
	}

	/**
	 * Returns a random date of birth in the format "dd mm yyyy".
	 *
	 * @return random date of birth as a String
	 */
	public static String randomDateOfBirth() {
		return OllamaClient.generateTestData("Generate a random date of birth in format dd mm yyyy.");
	}

	/**
	 * Returns a random gender value. The generator may return values like
	 * "Male", "Female", "Non-binary", etc. Tests should be prepared to
	 * handle any of the generated values.
	 *
	 * @return random gender as a String
	 */
	public static String randomGender() {
		return OllamaClient.generateTestData("Generate a random gender.");
	}

	/**
	 * Returns a random postal address.
	 *
	 * @return random address as a String
	 */
	public static String randomAddress() {
		return OllamaClient.generateTestData("Generate a random address.");
	}

	/**
	 * Returns a random city name.
	 *
	 * @return random city name as a String
	 */
	public static String randomCity() {
		return OllamaClient.generateTestData("Generate a random city name.");
	}

	/**
	 * Returns a random state name.
	 *
	 * @return random state name as a String
	 */
	public static String randomState() {
		return OllamaClient.generateTestData("Generate a random state name.");
	}

	/**
	 * Returns a random subject name (useful for education-related tests).
	 *
	 * @return random subject name as a String
	 */
	public static String randomSubject() {
		return OllamaClient.generateTestData("Generate a random subject name.");
	}

	/**
	 * Returns a random hobby from a small predefined set (Sports, Reading, Music).
	 *
	 * @return random hobby as a String
	 */
	public static String randomHobby() {
		return OllamaClient.generateTestData("Generate a random hobby from the list: Sports, Reading, Music.");
	}

}