package tests;

import utils.GPTClient;

public class AIGeneratedTest {
    public static void main(String[] args) throws Exception {
        String prompt = "Write a Selenium Java test that opens example.com, logs in with username and password, and verifies dashboard.";
        String testScript = GPTClient.ask(prompt);
        System.out.println("Generated Test:\n\n" + testScript);
    }
}
