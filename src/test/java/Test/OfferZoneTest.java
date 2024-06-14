package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import PageObject.BaseClass;
import PageObject.OfferZonePage;

import java.util.Set;

public class OfferZoneTest extends BaseClass {

    private static final Logger logger = LogManager.getLogger(OfferZoneTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 15, enabled = true)
    public void testAppleIpadLink() throws InterruptedException {
        ExtentReportManager.createTest("testAppleIpadLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        OfferZonePage offerZonePage = new OfferZonePage(driver);
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");

        // Hover over the "Login" element
        offerZonePage.hoverOverLogin();
        logger.info("User hovers over the Login element");
        extentTest.info("User hovers over the Login element");

        Thread.sleep(2000);
        // Click on the "Flipkart Plus Zone" link
        offerZonePage.clickFlipkartPlusZoneLink();
        logger.info("User clicks on the Flipkart Plus Zone link");
        extentTest.info("User clicks on the Flipkart Plus Zone link");

        Thread.sleep(2000);
        // Click on the "Offer Zone" link
        offerZonePage.clickOfferZoneLink();
        logger.info("User clicks on the Offer Zone link");
        extentTest.info("User clicks on the Offer Zone link");

        Thread.sleep(2000);
        // Click on the Apple iPads link
        offerZonePage.clickAppleIpadLink();
        logger.info("User clicks on the Apple iPads link");
        extentTest.info("User clicks on the Apple iPads link");

        // Assert whether the current URL is the Apple iPads page
        Assert.assertTrue(offerZonePage.isOnAppleIpadPage(), "Not on the Apple iPads page");
        logger.info("User is on the Apple iPads page");
        extentTest.pass("User is on the Apple iPads page");

        Thread.sleep(2000);
        // Click on the Apple iPad 10th Gen link
        offerZonePage.clickAppleIpad10thGenLink();
        logger.info("User clicks on the Apple iPad 10th Gen link");
        extentTest.info("User clicks on the Apple iPad 10th Gen link");

        Thread.sleep(3000);
        // Switch to the new window/tab
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // Click on Add to Cart
        offerZonePage.clickAddToCart();
        logger.info("User clicks on Add to Cart");

        Thread.sleep(2000);
        // Take screenshot after adding to cart
        String screenshotPath = captureScreenshot.captureScreenshot(driver, "OfferZonePage");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after adding Apple iPad to cart");

        // Assuming test passes, close the browser
        driver.close();
        driver.quit();
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
