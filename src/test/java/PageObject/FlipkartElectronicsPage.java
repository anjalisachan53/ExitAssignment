package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartElectronicsPage {
    private WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public FlipkartElectronicsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    @FindBy(className = "V+qh7D")
    WebElement targetElement;

    
    // Locate the Add to Cart button
    @FindBy(xpath = "//button[contains(@class, 'QqFHMw vslbG+ In9uk2')]")
    WebElement addToCartButton;
    

    
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

    
    // Hover over the "Electronics" element
    public void hoverOverElectronics() {
        WebElement electronicsElement = driver.findElement(By.xpath("//span[contains(@class, 'TSD49J') and text()='Electronics']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(electronicsElement).perform();
    }

    // Click on the "Mi" link
    public void clickMiLink() {
        WebElement miLink = driver.findElement(By.xpath("//a[@title='Mi' and contains(@href, '/mobiles/mi~brand/pr?sid=tyy,4io')]"));
        miLink.click();
    }

    // Check if the current URL is the Mi page
    public boolean isOnMiPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("mobiles/mi~brand/pr?sid=tyy,4io");
    }
    
    public void clickRedmi7ALink() {
        WebElement redmi7ALink = driver.findElement(By.xpath("//div[contains(@class, 'KzDlHZ') and text()='Redmi 7A (Matte Black, 32 GB)']"));
        redmi7ALink.click();
    }
}
