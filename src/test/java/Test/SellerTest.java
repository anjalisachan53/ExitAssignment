package Test;

import PageObject.BaseClass;
import PageObject.SellerPage;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

public class SellerTest extends BaseClass {
    private static final Logger logger = LogManager.getLogger(SellerTest.class);
    
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 20, enabled = true)
    public void testSellerPage() throws InterruptedException {
    	ExtentReportManager.createTest("testSellerTest");
        extentTest = ExtentReportManager.getTest();
    	
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        SellerPage sellerPage = new SellerPage(driver);
        logger.info("User launches Chrome browser and navigates to the Flipkart homepage");
        extentTest.info("User launches Chrome browser and navigates to the Flipkart homepage");
        
        // Click on the "Become a Seller" link
        sellerPage.clickBecomeASellerLink();
        logger.info("User clicks on the Become a Seller link");
        extentTest.info("User clicks on the Become a Seller link");
        
        Thread.sleep(2000);
        // Assert whether the "Start Selling" button is present
        Assert.assertTrue(sellerPage.isStartSellingButtonPresent(), "Start Selling button is not present on the page");
        logger.info("Start Selling button is present on the page");
        
        String screenshotPath = captureScreenshot.captureScreenshot(driver, "ProductFilter");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after applying product filter");

        driver.close();
        driver.quit();
        
    }
    
    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
    
}
