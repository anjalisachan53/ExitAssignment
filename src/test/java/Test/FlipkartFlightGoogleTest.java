package Test;

import PageObject.BaseClass;
import PageObject.FlipkartFlightGooglePage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartFlightGoogleTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartFlightGoogleTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 5, enabled = true)
    public void testGooglePlayLink() throws InterruptedException {
        ExtentReportManager.createTest("testGooglePlayLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartFlightGooglePage flightGooglePage = new FlipkartFlightGooglePage(driver);
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Hover over the "Login" element
        flightGooglePage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        Thread.sleep(2000);

        // Click on the "Flipkart Plus Zone" link
        flightGooglePage.clickFlipkartPlusZoneLink();
        logger.info("User clicks on the Flipkart Plus Zone link");
        extentTest.info("User clicks on the Flipkart Plus Zone link");

        Thread.sleep(2000);

        // Click on the "Flights" link
        flightGooglePage.clickFlightsLink();
        logger.info("User clicks on the Flights link");
        extentTest.info("User clicks on the Flights link");

        Thread.sleep(2000);

        // Click on the Google Play Store image
        flightGooglePage.clickGooglePlayImage();
        logger.info("User clicks on the Google Play Store image");
        extentTest.info("User clicks on the Google Play Store image");

        Thread.sleep(2000);
        try {
            // Assert whether the current URL is the Google Play Store page for the Flipkart app
            Assert.assertTrue(flightGooglePage.isOnGooglePlayPage(), "Not on the Google Play Store page for the Flipkart app");
            logger.info("User is on the Google Play Store page for the Flipkart app");
            extentTest.pass("User is on the Google Play Store page for the Flipkart app");
            
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "GooglePlayPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Google Play Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Google Play page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartFlightGoogleTest");
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
