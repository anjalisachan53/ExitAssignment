package Test;

import PageObject.BaseClass;
import PageObject.FlipkartSocialMediaPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartSocialMediaTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartSocialMediaTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 10, enabled = true)
    public void testYoutubeLink() throws InterruptedException {
        ExtentReportManager.createTest("testYoutubeLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartSocialMediaPage flipkartSocialMediaPage = new FlipkartSocialMediaPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Scroll to the bottom of the page
        flipkartSocialMediaPage.scrollToBottom();
        logger.info("User scrolls to the bottom of the page");
        extentTest.info("User scrolls to the bottom of the page");

        // Click on the YouTube icon
        flipkartSocialMediaPage.clickYoutubeIcon();
        logger.info("User clicks on the YouTube icon");
        extentTest.info("User clicks on the YouTube icon");

        Thread.sleep(2000);
        try {
            // Assert if the current URL is the Flipkart YouTube page
            Assert.assertTrue(flipkartSocialMediaPage.isOnYoutubePage(), "Not on the Flipkart YouTube page");
            logger.info("User is on the Flipkart YouTube page");
            extentTest.pass("User is on the Flipkart YouTube page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "FlipkartYoutubePage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Flipkart YouTube Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Flipkart YouTube page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartSocialMediaTest");
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
