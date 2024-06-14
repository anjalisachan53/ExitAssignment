package Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PageObject.BaseClass;
import PageObject.SearchPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class SearchTest extends BaseClass {
    SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger(SearchTest.class);
    ExcelUtils excelUtils;
    private ExtentTest extentTest;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        String excelPath = "C:\\Users\\anjalisachan\\Downloads\\ExitTest-2\\ExitTest\\TestData.xlsx"; // Replace with your actual file path
        String sheetName = "Sheet1"; // Replace with your actual sheet name
        excelUtils = new ExcelUtils(excelPath, sheetName);
        ExtentReportManager.getInstance(); // Ensure Extent Reports are initialized
    }

    @DataProvider(name = "searchData")
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

    @Test(priority = 19, dataProvider = "searchData", enabled = true)
    public void searchPageTest(String pageName, String dataToBeSearched, String executionRequired) throws InterruptedException {
        ExtentReportManager.createTest("searchPageTest - " + dataToBeSearched);
        extentTest = ExtentReportManager.getTest();
        
        searchPage = new SearchPage(driver);
        logger.info("User launches Chrome browser");
        extentTest.info("User launches Chrome browser");

        if (pageName.equalsIgnoreCase("searchpage") && executionRequired.equalsIgnoreCase("yes")) {
            logger.info("User enters the product to be searched and hits enter: " + dataToBeSearched);
            extentTest.info("User searches for " + dataToBeSearched);
            searchPage.searchProduct(dataToBeSearched);

            Thread.sleep(2000);
            String currentUrl = driver.getCurrentUrl();
            Assert.assertTrue(currentUrl.contains(dataToBeSearched), "URL does not contain the search query: " + dataToBeSearched);
        } else {
            logger.info("Skipping test as execution required is set to no for page: " + pageName);
            System.out.println("Skipping test as execution required is set to no for page: " + pageName); // Print statement for debugging
            extentTest.skip("Skipping test as execution required is set to no for page: " + pageName);
        }

        // Capture screenshot after each test iteration
        String screenshotPath = captureScreenshot.captureScreenshot(driver, "searchPageTest_" + dataToBeSearched);
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after searching for: " + dataToBeSearched);

        extentTest.pass("Verified search for " + dataToBeSearched + " applied successfully");
        
        driver.close();
        driver.quit();
    }
    
    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
