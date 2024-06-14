package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PageObject.BaseClass;
import PageObject.CustomerCarePage;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class CustomerCareTest extends BaseClass {
    CustomerCarePage customercarePage;
    private static final Logger logger = LogManager.getLogger(CustomerCareTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 2, enabled = true)
    public void testCustomerCare() throws InterruptedException {
        ExtentReportManager.createTest("testCustomerCare");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        customercarePage = new CustomerCarePage(driver);
        logger.info("User launches Chrome browser and navigates to the notification preferences page");
        extentTest.info("User launches Chrome browser and navigates to the notification preferences page");

        // Click on the three dots icon
        customercarePage.clickThreeDotsIcon();
        logger.info("User clicks on the three dots icon");
        extentTest.info("User clicks on the three dots icon");

        // Click on the "Notification Preferences" option
        customercarePage.clickCustomerCareOption();
        logger.info("User clicks on the Notification Preferences option");
        extentTest.info("User clicks on the Notification Preferences option");

        Thread.sleep(2000);
        try {
            WebElement helpCentreLink = driver.findElement(By.xpath("//a[text()='Help Centre']"));
            Assert.assertNotNull(helpCentreLink, "Help Centre link is not present on the page");
            logger.info("Help Centre is present on the page");
            extentTest.pass("Help Centre is present on the page");

            String screenshotPath = captureScreenshot.captureScreenshot(driver, "HelpCentre");
            extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after navigating to Help Centre");

            driver.close();
            driver.quit();
        } catch (Exception e) {
            extentTest.fail("Help Centre link is not present on the page: " + e.getMessage());
            String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_CustomerCareTest");
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
