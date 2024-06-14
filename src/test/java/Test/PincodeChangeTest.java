/*
package Test;

import PageObject.AddToCartPage;
import PageObject.BaseClass;
import PageObject.PincodeChangePage;
import PageObject.SearchPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import java.util.Set;

public class PincodeChangeTest extends BaseClass {
    PincodeChangePage pincodePage;
    AddToCartPage productPage;
    SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger(PincodeChangeTest.class);
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 16, enabled = true)
    public void testChangePincode() throws InterruptedException {
        ExtentReportManager.createTest("testChangePincode");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        pincodePage = new PincodeChangePage(driver);
        searchPage = new SearchPage(driver);
        logger.info("User launches Chrome browser and navigates to the pincode change page");
        extentTest.info("User launches Chrome browser and navigates to the pincode change page");

        searchPage.searchProduct("headphone");

        productPage = new AddToCartPage(driver);

        // Perform product click
        productPage.clickOnProduct();
        logger.info("User clicks on product");
        extentTest.info("User clicks on product");

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Thread.sleep(2000);

        // Enter a new pincode
        String newPincode = "123456";
        pincodePage.enterPincode(newPincode);

        // Click the "Change" button
        pincodePage.clickChangeButton();
        logger.info("User enters a new pincode and clicks on the Change button");
        extentTest.info("User enters a new pincode and clicks on the Change button");

        Thread.sleep(2000);
        String actualErrorMessage = pincodePage.getErrorMessageText();
        String expectedErrorMessage = "Not a valid pincode";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid pincode error message does not match");

        String screenshotPath = captureScreenshot.captureScreenshot(driver, "PincodeChangeTest");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after changing the pincode");

        logger.info("Verified the error message for invalid pincode");
        extentTest.pass("Verified the error message for invalid pincode");

        // Assuming test passes, close the browser
        driver.close();
        driver.quit();
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
*/




package Test;

import PageObject.AddToCartPage;
import PageObject.BaseClass;
import PageObject.PincodeChangePage;
import PageObject.SearchPage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import java.util.Set;

public class PincodeChangeTest extends BaseClass {
    PincodeChangePage pincodePage;
    AddToCartPage productPage;
    SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger(PincodeChangeTest.class);
    private ExtentTest extentTest;
    ExcelUtils excelUtils;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        String excelPath = "C:\\Users\\anjalisachan\\Downloads\\ExitTest-2\\ExitTest\\TestData.xlsx"; // Replace with your actual file path
        String sheetName = "Sheet2"; // Replace with your actual sheet name
        excelUtils = new ExcelUtils(excelPath, sheetName);
        ExtentReportManager.getInstance(); // Ensure Extent Reports are initialized
    }
    
    @DataProvider(name = "pincodeData")
    public Object[][] getData() {
        int rowCount = excelUtils.getRowCount();
        Object[][] data = new Object[rowCount][3]; // Updated to 3 columns

        for (int i = 1; i <= rowCount; i++) {
            data[i-1][0] = excelUtils.getCellData(i, 0); // Page Name
            data[i-1][1] = excelUtils.getCellData(i, 1); // Data to be Searched
            data[i-1][2] = excelUtils.getCellData(i, 2); // Execution Required
            logger.info("Data read from Excel - Row {}: {}, {}, {}", i, data[i-1][0], data[i-1][1], data[i-1][2]);
        }
        return data;
    }

    @Test(priority = 16, enabled = true,  dataProvider = "pincodeData")
    public void testChangePincode(String pageName, String dataToBeSearched, String executionRequired) throws InterruptedException {
        ExtentReportManager.createTest("testChangePincode");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        
        
        if (pageName.equalsIgnoreCase("pincodepage") && executionRequired.equalsIgnoreCase("yes")) {
        pincodePage = new PincodeChangePage(driver);
        searchPage = new SearchPage(driver);
        logger.info("User launches Chrome browser and navigates to the pincode change page");
        extentTest.info("User launches Chrome browser and navigates to the pincode change page");

        searchPage.searchProduct("headphone");

        productPage = new AddToCartPage(driver);

        // Perform product click
        productPage.clickOnProduct();
        logger.info("User clicks on product");
        extentTest.info("User clicks on product");

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        Thread.sleep(2000);

        // Enter a new pincode
        //String newPincode = "123456";
        pincodePage.enterPincode(dataToBeSearched);

        // Click the "Change" button
        Thread.sleep(1000);
        pincodePage.clickChangeButton();
        logger.info("User enters a new pincode and clicks on the Change button");
        extentTest.info("User enters a new pincode and clicks on the Change button");

        Thread.sleep(2000);
        String actualErrorMessage = pincodePage.getErrorMessageText();
        String expectedErrorMessage = "Not a valid pincode";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Invalid pincode error message does not match");

        String screenshotPath = captureScreenshot.captureScreenshot(driver, "PincodeChangeTest");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after changing the pincode");

        logger.info("Verified the error message for invalid pincode");
        extentTest.pass("Verified the error message for invalid pincode");

        // Assuming test passes, close the browser
        driver.close();
        driver.quit();
        } else {
            logger.info("Skipping test as execution required is set to no for page: " + pageName);
            System.out.println("Skipping test as execution required is set to no for page: " + pageName); // Print statement for debugging
            extentTest.skip("Skipping test as execution required is set to no for page: " + pageName);
        }
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
