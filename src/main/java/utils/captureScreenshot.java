
package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class captureScreenshot {
    public static String captureScreenshot(WebDriver driver, String ssname) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = "screenshots/" + ssname + "_" + timeStamp + ".jpg";
        
        try {
            Path destination = Paths.get(destPath).toAbsolutePath();
            Files.createDirectories(destination.getParent());
            Files.copy(screenshot.toPath(), destination);
            return destPath; // Return the path where the screenshot is saved
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}






/*
package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class captureScreenshot {
    public static String captureScreenshot(WebDriver driver, String ssname) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        // Create screenshots directory if it doesn't exist
        String screenshotsDir = "src" + File.separator + "main" + File.separator + "java" + File.separator + "utils" + File.separator + "screenshots";
        File screenshotsFolder = new File(screenshotsDir);
        if (!screenshotsFolder.exists()) {
            screenshotsFolder.mkdirs();
        }

        String destPath = screenshotsDir + File.separator + ssname + "_" + timeStamp + ".jpg";
        
        try {
            Path destination = Paths.get(destPath).toAbsolutePath();
            Files.copy(screenshot.toPath(), destination);
            return destPath; // Return the path where the screenshot is saved
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}*/

