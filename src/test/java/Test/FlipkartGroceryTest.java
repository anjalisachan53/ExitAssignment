package Test;

import PageObject.BaseClass;
import PageObject.FlipkartGroceryPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartGroceryTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(FlipkartGroceryTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 7, enabled = true)
    public void testGroceryLink() throws InterruptedException {
        ExtentReportManager.createTest("testGroceryLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartGroceryPage flipkartGroceryPage = new FlipkartGroceryPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Click on the Grocery element
        flipkartGroceryPage.clickGrocery();
        logger.info("User clicks on the Grocery element");
        extentTest.info("User clicks on the Grocery element");

        try {
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "GroceryPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Grocery Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to find Verify Delivery Pincode element or Deals of the Day element: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartGroceryTest");
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
