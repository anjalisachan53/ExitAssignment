package Test;

import PageObject.BaseClass;
import PageObject.FlipkartCareerPage;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class FlipkartCareerTest extends BaseClass {
    
    private static final Logger logger = LogManager.getLogger(FlipkartCareerTest.class);

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 3, enabled = true)
    public void testCareerLink() throws InterruptedException {
        ExtentReportManager.createTest("testCareerLink");
        ExtentTest extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartCareerPage flipkartCareerPage = new FlipkartCareerPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Scroll to the bottom of the page
        flipkartCareerPage.scrollToBottom();
        logger.info("User scrolls to the bottom of the page");
        extentTest.info("User scrolls to the bottom of the page");

        Thread.sleep(2000);
        // Click on the Careers link
        flipkartCareerPage.clickCareers();
        logger.info("User clicks on the Careers link");
        extentTest.info("User clicks on the Careers link");
        
        Thread.sleep(2000);
        try {
            // Assert if the current URL is the Flipkart Careers page
            Assert.assertTrue(flipkartCareerPage.isOnCareersPage(), "Not on the Flipkart Careers page");
            logger.info("User is on the Flipkart Careers page");
            extentTest.pass("User is on the Flipkart Careers page");
            
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "CareerTest");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Career Page");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Not on the Flipkart Careers page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartCareerTest");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on error");
            throw e; // Re-throw the exception to ensure test fails
        }

        
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
