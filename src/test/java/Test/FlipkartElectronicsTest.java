package Test;

import PageObject.AddToCartPage;
import PageObject.BaseClass;
import PageObject.FlipkartElectronicsPage;
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

public class FlipkartElectronicsTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(FlipkartElectronicsTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 4, enabled = true)
    public void testMiLink() throws InterruptedException {
        ExtentReportManager.createTest("testMiLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartPlusPage flipkartPlusPage = new FlipkartPlusPage(driver);
        FlipkartElectronicsPage electronicsPage = new FlipkartElectronicsPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        flipkartPlusPage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        // Click on the "Flipkart Plus Zone" link
        flipkartPlusPage.clickFlipkartPlusZoneLink();
        logger.info("User clicks on the Flipkart Plus Zone link");
        extentTest.info("User clicks on the Flipkart Plus Zone link");

        // Hover over the "Electronics" element
        electronicsPage.hoverOverElectronics();
        logger.info("User hovers over the Electronics element");
        extentTest.info("User hovers over the Electronics element");

        Thread.sleep(2000);

        // Click on the "Mi" link
        electronicsPage.clickMiLink();
        logger.info("User clicks on the Mi link");
        extentTest.info("User clicks on the Mi link");
        Thread.sleep(2000);
        
        try {
            // Assert whether the current URL is the Mi page
            electronicsPage.clickRedmi7ALink();
            logger.info("User is on the Mi page");
            extentTest.pass("User is on the Mi page");
            
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "MiPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Mi Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Mi page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartElectronicsTest");
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
