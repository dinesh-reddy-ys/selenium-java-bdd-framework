package utils;

/**
 * Utility class for generating test data. This can be extended to include
 * methods for generating random strings, numbers, dates, etc. as needed for
 * testing.
 */

public class TestDataGenerator {

	public static String randomFirstName() {
		return OllamaClient.generateTestData("Generate a random first name.");
	}

	public static String randomLastName() {
		return OllamaClient.generateTestData("Generate a random last name.");
	}

	public static String randomEmail() {
		return OllamaClient.generateTestData("Generate a random valid email address.");
	}

	public static String randomAge() {
		return OllamaClient.generateTestData("Generate a random age between 18 and 100.");
	}

	public static String randomSalary() {
		return OllamaClient.generateTestData("Generate a random salary between 30000 and 150000.");
	}

	public static String randomDepartment() {
		return OllamaClient.generateTestData("Generate a random department name.");
	}

	public static String randomMobileNumber() {
		return OllamaClient.generateTestData("Generate a random 10-digit mobile number.");
	}

	public static String randomDateOfBirth() {
		return OllamaClient.generateTestData("Generate a random date of birth in format dd mm yyyy.");
	}
	
	public static String randomGender() {
		return OllamaClient.generateTestData("Generate a random gender.");
	}
	
	public static String randomAddress() {
		return OllamaClient.generateTestData("Generate a random address.");
	}
	
	public static String randomCity() {
		return OllamaClient.generateTestData("Generate a random city name.");
	}
	
	public static String randomState() {
		return OllamaClient.generateTestData("Generate a random state name.");
	}
	
	public static String randomSubject() {
		return OllamaClient.generateTestData("Generate a random subject name.");
	}
	
	public static String randomHobby() {
		return OllamaClient.generateTestData("Generate a random hobby from the list: Sports, Reading, Music.");
	}

}
