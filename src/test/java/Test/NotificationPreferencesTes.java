package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import PageObject.BaseClass;
import PageObject.NotificationPreferencePage;

public class NotificationPreferencesTes extends BaseClass {
    NotificationPreferencePage notificationPage;
    private static final Logger logger = LogManager.getLogger(NotificationPreferencesTes.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 14, enabled = true)
    public void testNotificationPreferences() throws InterruptedException {
        ExtentReportManager.createTest("testNotificationPreferences");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        notificationPage = new NotificationPreferencePage(driver);
        logger.info("User launches Chrome browser and navigates to the notification preferences page");
        extentTest.info("User launches Chrome browser and navigates to the notification preferences page");

        // Click on the three dots icon
        notificationPage.clickThreeDotsIcon();
        logger.info("User clicks on the three dots icon");
        extentTest.info("User clicks on the three dots icon");

        // Click on the "Notification Preferences" option
        notificationPage.clickNotificationPreferencesOption();
        logger.info("User clicks on the Notification Preferences option");
        extentTest.info("User clicks on the Notification Preferences option");

        Thread.sleep(2000);
        try {
            WebElement desktopNotificationsHeading = driver.findElement(By.xpath("//h2[text()='Desktop Notifications']"));
            Assert.assertNotNull(desktopNotificationsHeading, "Desktop Notifications heading is not present on the page");
            logger.info("Desktop Notifications heading is present on the page");
            extentTest.pass("Desktop Notifications heading is present on the page");

            // Take screenshot after verifying Desktop Notifications heading
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "NotificationPreferences");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after verifying Notification Preferences");

            // Assuming test passes, close the browser
            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Desktop Notifications heading is not present on the page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_NotificationPreferencesTest");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on error");

            // Close the browser in case of failure
            driver.close();
            driver.quit();
            throw e; // Re-throw the exception to ensure test fails
        }
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
