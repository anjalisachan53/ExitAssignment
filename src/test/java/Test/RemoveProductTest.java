package Test;

import PageObject.AddToCartPage;
import PageObject.BaseClass;
import PageObject.RemovePage;
import PageObject.SearchPage;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import utils.ExtentReportManager;
import utils.captureScreenshot;

public class RemoveProductTest extends BaseClass {
    RemovePage cartPage;
    SearchPage searchPage;
    AddToCartPage productPage;
    private static final Logger logger = LogManager.getLogger(RemoveProductTest.class);
    private ExtentTest extentTest;
    
    @BeforeClass
    public void setup() {
        System.setProperty("log4j.configurationFile", "log4j2.properties");
        ExtentReportManager.getInstance();
    }

    @Test(priority = 18, enabled = true)
    public void testRemoveItemFromCart() throws InterruptedException {
        ExtentReportManager.createTest("testRemoveItemFromCart");
        extentTest = ExtentReportManager.getTest();

        System.setProperty("log4j.configurationFile", "log4j2.properties");
        searchPage = new SearchPage(driver);
        cartPage = new RemovePage(driver);
        productPage = new AddToCartPage(driver);
        logger.info("User launches Chrome browser");
        extentTest.info("User launches Chrome browser");

        // Perform the search for "headphones"
        searchPage.searchProduct("headphone");
        extentTest.info("User searches for 'headphone'");

        // Perform product click
        productPage.clickOnProduct();
        extentTest.info("User clicks on product and adds it to cart");

        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2000);
        // Perform Add to Cart action
        productPage.clickAddToCart();
        extentTest.info("Product added to the cart");

        logger.info("Verified that the product is added to the cart and navigated to the cart page");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Click the "Remove" button
        cartPage.clickRemoveButton();
        extentTest.info("User clicks on the 'Remove' button");

        Thread.sleep(2000);
        cartPage.clickConfirmRemoveButton();
        extentTest.info("User confirms the removal of the product from the cart");

        logger.info("Product removed from the cart");

        Thread.sleep(2000);

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPage.getSuccessMessageLocator()));
        String expectedMessage = "Successfully removed OPPO Enco Buds 2 with 28 hours Battery life & Deep Noise Cancellation Bluetooth Headset from your cart";
        String actualMessage = successMessage.getText();
        Assert.assertEquals(actualMessage, expectedMessage, "The success message does not match the expected message");
        extentTest.pass("Verified the success message for removing the product from the cart");

        // Capture screenshot after successful removal
        String screenshotPath = captureScreenshot.captureScreenshot(driver, "RemoveProduct");
        extentTest.addScreenCaptureFromPath(screenshotPath, "Screenshot after product removal from cart");

        // Assuming test passes, close the browser
        driver.close();
        driver.quit();
    }
    
    @AfterClass
    public void tearDown() {
        ExtentReportManager.flush();
    }
}
