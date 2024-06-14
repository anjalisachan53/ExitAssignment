package Test;

import PageObject.BaseClass;
import PageObject.FlipkartMyntraPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class FlipkartMyntraTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(FlipkartMyntraTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 8, enabled = true)
    public void testMyntraLink() throws InterruptedException {
        ExtentReportManager.createTest("testMyntraLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        FlipkartMyntraPage flipkartMyntraPage = new FlipkartMyntraPage(driver);
        
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Scroll to the bottom of the page
        flipkartMyntraPage.scrollToBottom();
        logger.info("User scrolls to the bottom of the page");
        extentTest.info("User scrolls to the bottom of the page");

        // Click on the Myntra link
        flipkartMyntraPage.clickMyntraLink();
        logger.info("User clicks on the Myntra link");
        extentTest.info("User clicks on the Myntra link");

        // Switch to the new window/tab
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        try {
            // Assert if the current URL is the Myntra page
            Assert.assertTrue(flipkartMyntraPage.isOnMyntraPage(), "Not on the Myntra page");
            logger.info("User is on the Myntra page");
            extentTest.pass("User is on the Myntra page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "MyntraPage");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Myntra Page");

            driver.close();
            driver.switchTo().window(originalWindow);
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Failed to navigate to Myntra page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_FlipkartMyntraTest");
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
