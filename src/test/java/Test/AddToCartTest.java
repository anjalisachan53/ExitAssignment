/*
package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObject.BaseClass;
import PageObject.SearchPage;
import PageObject.AddToCartPage;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddToCartTest extends BaseClass {
	AddToCartPage productPage;
	SearchPage searchPage;
	private static final Logger logger = LogManager.getLogger(AddToCartTest.class);

    @Test
    public void testAddProductToCart() throws InterruptedException {
    	System.setProperty("log4j.configurationFile", "log4j2.properties");
    	
    	 searchPage = new SearchPage(driver);
         // Perform the search for "headphones"
         searchPage.searchProduct("headphone");
         
         logger.info("User enters the product to be searched and hits enter");
    	
    	
        // Create an instance of ProductPage
        productPage = new AddToCartPage(driver);
        
        // Perform product click
        productPage.clickOnProduct();
        logger.info("User clicks on product and clicks on add to cart button");
        
        
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        
        Thread.sleep(2000);
        // Perform Add to Cart action
        productPage.clickAddToCart();

        
        logger.info("Verified that the product is added to the cart and navigated to the cart page");
        Thread.sleep(2000);
        
        // Close the new tab and switch back to the original window
        driver.close();
        
    }
}
*/

package Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import PageObject.BaseClass;
import PageObject.SearchPage;
import PageObject.AddToCartPage;
import utils.ExcelUtils;
import utils.captureScreenshot;
import utils.ExtentReportManager;

import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddToCartTest extends BaseClass {
    AddToCartPage productPage;
    SearchPage searchPage;
    private static final Logger logger = LogManager.getLogger(AddToCartTest.class);
    ExcelUtils excelUtils;

    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        String excelPath = "C:\\Users\\anjalisachan\\Downloads\\ExitTest-2\\ExitTest\\TestData.xlsx"; // Replace with your actual file path
        String sheetName = "Sheet1"; // Replace with your actual sheet name
        excelUtils = new ExcelUtils(excelPath, sheetName);

        ExtentReportManager.getInstance();
    }

    @DataProvider(name = "addToCartData")
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

    @Test(priority = 1, dataProvider = "addToCartData", enabled = true)
    public void testAddProductToCart(String pageName, String dataToBeSearched, String executionRequired) throws InterruptedException {
        ExtentReportManager.createTest("testAddProductToCart - " + pageName);
        ExtentTest extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");

        if (executionRequired.equalsIgnoreCase("yes")) {
            searchPage = new SearchPage(driver);

            if (pageName.equalsIgnoreCase("addtocartpage")) {
                try {
                    // Perform the search for the product
                    logger.info("User enters the product to be searched and hits enter: " + dataToBeSearched);
                    extentTest.info("User enters the product to be searched and hits enter: " + dataToBeSearched);
                    searchPage.searchProduct(dataToBeSearched);

                    // Create an instance of ProductPage
                    productPage = new AddToCartPage(driver);

                    // Perform product click
                    productPage.clickOnProduct();
                    extentTest.info("User clicks on product and clicks on add to cart button");
                    logger.info("User clicks on product and clicks on add to cart button");

                    String originalWindow = driver.getWindowHandle();
                    Set<String> allWindows = driver.getWindowHandles();
                    for (String window : allWindows) {
                        if (!window.equals(originalWindow)) {
                            driver.switchTo().window(window);
                            break;
                        }
                    }

                    Thread.sleep(2000);
                    // Perform Add to Cart action
                    productPage.clickAddToCart();

                    extentTest.info("Verified that the product is added to the cart and navigated to the cart page");
                    logger.info("Verified that the product is added to the cart and navigated to the cart page");
                    System.out.println("Verified that the product is added to the cart and navigated to the cart page"); // Print statement for debugging
                    
                    Thread.sleep(2000);

                    // Capture screenshot after adding to cart
                    String screenshotPath = captureScreenshot.captureScreenshot(driver, "AddedToCart_" + pageName);
                    extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after adding to cart");
                    
                    // Close the new tab and switch back to the original window
                    driver.close();
                    driver.quit();
                    extentTest.pass("Test passed successfully");
                } catch (Exception e) {
                    extentTest.fail("Test failed due to exception: " + e.getMessage());
                    String screenshotPath = captureScreenshot.captureScreenshot(driver, "Error_" + pageName);
                    extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot on error");
                    throw e; // Re-throw the exception to ensure test fails
                }
            }
        } else {
            extentTest.skip("Skipping test as execution required is set to no for page: " + pageName);
            logger.info("Skipping test as execution required is set to no for page: " + pageName);
            System.out.println("Skipping test as execution required is set to no for page: " + pageName); // Print statement for debugging
            //driver.close();
            //driver.quit();
        }
    }

    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
