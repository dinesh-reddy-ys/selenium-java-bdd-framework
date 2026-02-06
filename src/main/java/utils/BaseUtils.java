package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseUtils {

    /**
     * Capture screenshot and store inside Extent report folder.
     * Returns relative path for Extent report usage.
     */
    public static String takeScreenshot(String testName)
            throws IOException {

        WebDriver driver = DriverFactory.getDriver();

        String timestamp =
                new SimpleDateFormat("yyyyMMdd_HHmmss")
                        .format(new Date());

        // Folder inside extent report
        String reportScreenshotDir =
                System.getProperty("user.dir")
                + "/test-output/extent-reports/screenshots/";

        // Create folder if missing
        new File(reportScreenshotDir).mkdirs();

        // Clean file name
        String fileName =
                testName.replaceAll("\\s+", "_")
                        + "_" + timestamp + ".png";

        String fullPath =
                reportScreenshotDir + fileName;

        File srcFile =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile,
                new File(fullPath));

        // Return relative path for Extent
        return "screenshots/" + fileName;
    }

    /**
     * Capture element screenshot.
     */
    public static void captureElementScreenshot(
            WebElement element,
            String elementName) throws IOException {

        String dir =
                System.getProperty("user.dir")
                + "/test-output/extent-reports/screenshots/elements/";

        new File(dir).mkdirs();

        String fileName =
                elementName.replaceAll("\\s+", "_")
                        + "_" + System.currentTimeMillis()
                        + ".png";

        File srcFile =
                element.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile,
                new File(dir + fileName));

        System.out.println(
                "Element screenshot saved: " + fileName);
    }
}
