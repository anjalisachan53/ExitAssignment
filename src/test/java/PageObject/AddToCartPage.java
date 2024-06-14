package PageObject;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddToCartPage {
    WebDriver driver;
    WebDriverWait wait;

    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locate the product link
    @FindBy(xpath = "//a[contains(@title, 'OPPO Enco Buds 2 with 28 hours Battery life & Deep Noise Cancellation Bluetooth Headset')]")
    WebElement productLink;
    
    @FindBy(className = "V+qh7D")
    WebElement targetElement;

    
    // Locate the Add to Cart button
    @FindBy(xpath = "//button[contains(@class, 'QqFHMw vslbG+ In9uk2')]")
    WebElement addToCartButton;
    

    // Method to click on the product link
    public void clickOnProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
    }

    
    // Method to click on the Add to Cart button
    public void clickAddToCart() {
    	
        // Scroll to the target element
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);

        // Wait for a short period to ensure scrolling is complete
        try {
            Thread.sleep(2000); // Use WebDriverWait if preferred
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ngOQ7L")));

    	wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
    }
    
}
