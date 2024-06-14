package Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PageObject.BaseClass;
import PageObject.ProductFilterPage;
import PageObject.SearchPage;
import utils.ExcelUtils;
import utils.ExtentReportManager;
import utils.captureScreenshot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ProductFilter extends BaseClass {
    ProductFilterPage productFilterPage;
    SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger(ProductFilter.class);
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
    
    @DataProvider(name = "productData")
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

    @Test(priority = 17, enabled = true, dataProvider = "productData")
    public void testApplySamsungFilter(String pageName, String dataToBeSearched, String executionRequired) throws InterruptedException {
    	ExtentReportManager.createTest("testProductFilter");
        extentTest = ExtentReportManager.getTest();
    	
    	System.setProperty("log4j.configurationFile", "log4j2.properties");
        
    	if (pageName.equalsIgnoreCase("productfilterpage") && executionRequired.equalsIgnoreCase("yes")) {
        productFilterPage = new ProductFilterPage(driver);
        
        
        searchPage = new SearchPage(driver);
        logger.info("User launches Chrome browser");
        extentTest.info("User launches Chrome browser");

        
        searchPage.searchProduct(dataToBeSearched);
        extentTest.info("User clicks on " + dataToBeSearched);
        
        productFilterPage.clickSamsungFilter();
        extentTest.info("User applies Samsung filter");
        
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        String expectedUrlPart = "p%5B%5D=facets.brand%255B%255D%3DSAMSUNG";
        Assert.assertTrue(currentUrl.contains(expectedUrlPart), "URL does not contain the expected Samsung filter parameter");

        
        String screenshotPath = captureScreenshot.captureScreenshot(driver, "ProductFilter");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after applying product filter");
        
        extentTest.pass("Verified filter for " + dataToBeSearched + " applied successfully");
        
        driver.close();
        driver.quit();
        
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