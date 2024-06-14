package Test;

import PageObject.BaseClass;
import PageObject.GiftCardPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class GiftCardTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(GiftCardTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 12, enabled = true)
    public void testGiftCardPage() throws InterruptedException {
        ExtentReportManager.createTest("testGiftCardPage");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        GiftCardPage giftCardPage = new GiftCardPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Hover over the "Login" element
        giftCardPage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        // Click on the "Gift Cards" link
        giftCardPage.clickGiftCardsLink();
        logger.info("User clicks on the Gift Cards link");
        extentTest.info("User clicks on the Gift Cards link");

        Thread.sleep(2000);
        try {
            // Assert whether the current URL is the Gift Cards page
            Assert.assertTrue(giftCardPage.isOnGiftCardPage(), "Not on the Gift Cards page");
            logger.info("User is on the Gift Cards page");
            extentTest.pass("User is on the Gift Cards page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "GiftCardPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Gift Cards Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Gift Cards page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_GiftCardTest");
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
