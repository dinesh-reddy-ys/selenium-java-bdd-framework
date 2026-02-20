package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties = new Properties();

    static {
        try {
            // Read ENV passed from Jenkins
            String env = System.getProperty("ENV");

            if (env == null) {
                env = "UAT";  // Default environment
            }

            System.out.println("Running tests on environment: " + env);

            // Load respective config file
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config-" + env + ".properties");

            properties.load(fis);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
