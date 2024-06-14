package Test;

import PageObject.BaseClass;
import PageObject.FlipkartPlusPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartPlusTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartPlusTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 9, enabled = true)
    public void testFlipkartPlusPage() throws InterruptedException {
        ExtentReportManager.createTest("testFlipkartPlusPage");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartPlusPage flipkartPlusPage = new FlipkartPlusPage(driver);
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Hover over the "Login" element
        flipkartPlusPage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        // Click on the "Flipkart Plus Zone" link
        flipkartPlusPage.clickFlipkartPlusZoneLink();
        logger.info("User clicks on the Flipkart Plus Zone link");
        extentTest.info("User clicks on the Flipkart Plus Zone link");

        Thread.sleep(2000);
        try {
            // Assert whether the current URL is the Flipkart Plus page
            Assert.assertTrue(flipkartPlusPage.isOnFlipkartPlusPage(), "Not on the Flipkart Plus page");
            logger.info("User is on the Flipkart Plus page");
            extentTest.pass("User is on the Flipkart Plus page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "FlipkartPlusPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Flipkart Plus Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Flipkart Plus page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartPlusTest");
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
