package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtils {
    public WebDriver driver;
    public static void takeScreenshot(String testName) throws IOException {
        WebDriver driver = DriverFactory.getDriver();

        // Timestamp for unique filenames
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Define destination path
        String path = "screenshots/" + testName + "_" + timestamp + ".png";
        FileUtils.copyFile(srcFile, new File(path));

        System.out.println("Screenshot saved to: " + path);
    }
    public static void captureElementScreenshot(WebElement element, String elementName) throws IOException {
        // Create screenshot file
        File srcFile = element.getScreenshotAs(OutputType.FILE);

        // Create destination path with timestamp
        String fileName = elementName + "_" + System.currentTimeMillis() + ".png";
        String filePath = "screenshots/elements/" + fileName;

        // Save the file
        FileUtils.copyFile(srcFile, new File(filePath));
        System.out.println("Element screenshot saved to: " + filePath);
    }
}
