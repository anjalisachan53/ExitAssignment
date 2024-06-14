package Test;

import PageObject.BaseClass;
import PageObject.FlipkartFlightPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartFlightTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartFlightTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 6, enabled = true)
    public void testFlightsLink() throws InterruptedException {
        ExtentReportManager.createTest("testFlightsLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartFlightPage flightPage = new FlipkartFlightPage(driver);
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Hover over the "Login" element
        flightPage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        Thread.sleep(2000);

        // Click on the "Flipkart Plus Zone" link
        flightPage.clickFlipkartPlusZoneLink();
        logger.info("User clicks on the Flipkart Plus Zone link");
        extentTest.info("User clicks on the Flipkart Plus Zone link");

        Thread.sleep(2000);

        // Click on the "Flights" link
        flightPage.clickFlightsLink();
        logger.info("User clicks on the Flights link");
        extentTest.info("User clicks on the Flights link");

        Thread.sleep(2000);
        try {
            // Assert whether the current URL is the Flights page
            Assert.assertTrue(flightPage.isOnFlightsPage(), "Not on the Flights page");
            logger.info("User is on the Flights page");
            extentTest.pass("User is on the Flights page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "FlightsPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Flights Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Flights page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartFlightTest");
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
