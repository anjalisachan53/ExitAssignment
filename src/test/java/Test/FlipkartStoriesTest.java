package Test;

import PageObject.BaseClass;
import PageObject.FlipkartStoriesPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartStoriesTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartStoriesTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 11, enabled = true)
    public void testFlipkartStoriesSubscribe() throws InterruptedException {
        ExtentReportManager.createTest("testFlipkartStoriesSubscribe");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartStoriesPage flipkartStoriesPage = new FlipkartStoriesPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Scroll to the bottom of the page
        flipkartStoriesPage.scrollToBottom();
        logger.info("User scrolls to the bottom of the page");
        extentTest.info("User scrolls to the bottom of the page");

        // Click on the Flipkart Stories link
        flipkartStoriesPage.clickFlipkartStoriesLink();
        logger.info("User clicks on the Flipkart Stories link");
        extentTest.info("User clicks on the Flipkart Stories link");

        // Switch to the new window/tab
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Click on the Subscribe button
        flipkartStoriesPage.clickSubscribeButton();
        logger.info("User clicks on the Subscribe button");
        extentTest.info("User clicks on the Subscribe button");

        Thread.sleep(2000);
        try {
            // Assert if the current URL is the Subscribe page
            Assert.assertTrue(flipkartStoriesPage.isOnSubscribePage(), "Not on the Subscribe page");
            logger.info("User is on the Subscribe page");
            extentTest.pass("User is on the Subscribe page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "FlipkartStoriesSubscribePage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after subscribing to Flipkart Stories");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Subscribe page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartStoriesTest");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on error");

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
