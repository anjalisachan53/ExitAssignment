package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import PageObject.BaseClass;
import PageObject.LoginPage;

public class LoginTest extends BaseClass {
    LoginPage loginPage;
    private static final Logger logger = LogManager.getLogger(LoginTest.class);
    private ExtentTest extentTest;
    ExcelUtils excelUtils;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        String excelPath = "C:\\Users\\anjalisachan\\Downloads\\ExitTest-2\\ExitTest\\TestData.xlsx"; // Replace with your actual file path
        String sheetName = "Sheet1"; // Replace with your actual sheet name
        excelUtils = new ExcelUtils(excelPath, sheetName);

        ExtentReportManager.getInstance();
    }

    @DataProvider(name = "loginData")
    public Object[][] getData() {
        int rowCount = excelUtils.getRowCount();
        Object[][] data = new Object[rowCount][3]; // Update to include the third column

        for (int i = 1; i <= rowCount; i++) {
            data[i-1][0] = excelUtils.getCellData(i, 0); // Page Name
            data[i-1][1] = excelUtils.getCellData(i, 1); // Data to be Searched
            data[i-1][2] = excelUtils.getCellData(i, 2); // Execution Required
            logger.info("Data read from Excel - Row {}: {}, {}, {}", i, data[i-1][0], data[i-1][1], data[i-1][2]);
            System.out.println("Data read from Excel - Row " + i + ": " + data[i-1][0] + ", " + data[i-1][1] + ", " + data[i-1][2]); // Print statement for debugging
        }
        return data;
    }

    @Test(priority = 13, enabled = true, dataProvider = "loginData")
    public void testLoginLink(String pageName, String emailToBeEntered, String executionRequired) {
        ExtentReportManager.createTest("testLoginLink");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        
        if (executionRequired.equalsIgnoreCase("yes")) { 
        	loginPage = new LoginPage(driver);
        	if(pageName.equalsIgnoreCase("loginpage")) {
                try {
                    logger.info("User launches Chrome browser and opens login");
                    extentTest.info("User launches Chrome browser and opens login");

                    loginPage.clickLoginLink();
                    logger.info("User clicks on the Login link");
                    extentTest.info("User clicks on the Login link");
                    
                    Thread.sleep(2000);
                    loginPage.enterEmail(emailToBeEntered);
                    logger.info("User enters email: " + emailToBeEntered);
                    extentTest.info("User enters email: " + emailToBeEntered);
                    
                   
                    // Verify that the URL is correct after clicking the login link
                    String expectedUrl = "https://www.flipkart.com/account/login?ret=/";
                    String actualUrl = driver.getCurrentUrl();
                    logger.info("Expected and Actual Url matches");
                    extentTest.info("Expected and Actual Url matches");
                    Assert.assertEquals(actualUrl, expectedUrl, "URL after clicking login link does not match expected URL");

                    // Assuming successful assertion means login link works as expected
                    extentTest.pass("URL after clicking login link matches expected URL");
                    
                    String screenshotPath = captureScreenshot.captureScreenshot(driver, "LoginTest");
                    extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after verifying login URL");
                    driver.close();
                    driver.quit();
                } catch (Exception e) {
                    extentTest.fail("Test failed due to exception: " + e.getMessage());
                    String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_" + pageName);
                    extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on error");
                    //throw e; // Re-throw the exception to ensure test fails
                } 
        	}
        	} else {
                extentTest.skip("Skipping test as execution required is set to no for page: " + pageName);
                logger.info("Skipping test as execution required is set to no for page: " + pageName);
                System.out.println("Skipping test as execution required is set to no for page: " + pageName); // Print statement for debugging
                
             }
        }
    

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
