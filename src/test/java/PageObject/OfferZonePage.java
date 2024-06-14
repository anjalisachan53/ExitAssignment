package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OfferZonePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public OfferZonePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }
    
    @FindBy(className = "ttP6QY")
    WebElement targetElement;

    
    // Locate the Add to Cart button
    @FindBy(xpath = "//button[contains(@class, 'QqFHMw vslbG+ In9uk2')]")
    WebElement addToCartButton;

    // Hover over the "Login" element
    public void hoverOverLogin() {
        WebElement loginElement = driver.findElement(By.xpath("//div[@class='H6-NpN _3N4_BX']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(loginElement).perform();
    }

    // Click on the "Flipkart Plus Zone" link
    public void clickFlipkartPlusZoneLink() {
        WebElement flipkartPlusZoneLink = driver.findElement(By.xpath("//li[contains(., 'Flipkart Plus Zone')]"));
        flipkartPlusZoneLink.click();
    }

    // Click on the "Offer Zone" link
    public void clickOfferZoneLink() {
        WebElement offerZoneLink = driver.findElement(By.xpath("//a[@class='TSD49J' and @href='/offers-list/top-deals?screen=dynamic&pk=themeViews%3DDT-OMU-A2%3ADT-OMU~widgetType%3DdealCard~contentType%3Dneo&otracker=nmenu_offer-zone']"));
        offerZoneLink.click();
    }

    // Click on the Apple iPads link
    public void clickAppleIpadLink() {
        WebElement appleIpadLink = driver.findElement(By.xpath("//div[@class='ZHvV68' and text()='Apple iPads']"));
        appleIpadLink.click();
    }
    
    public void clickAppleIpad10thGenLink() {
        WebElement appleIpad10thGenLink = driver.findElement(By.xpath("//div[@class='KzDlHZ' and text()='Apple iPad (10th Gen) 64 GB ROM 10.9 inch with Wi-Fi Only (Blue)']"));
        appleIpad10thGenLink.click();
    }

    // Check if the current URL is the Apple iPads page
    public boolean isOnAppleIpadPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("tablets/pr?sid=tyy%2Chry&marketplace=FLIPKART&p%5B%5D=facets.fulfilled_by%255B%255D%3DFlipkart%2BAssured&p%5B%5D=facets.brand%255B%255D%3DApple&p%5B%5D=facets.availability%255B%255D%3DExclude%2BOut%2Bof%2BStock");
    }
    
    
    
    public void clickAddToCart() throws InterruptedException {
        // Wait until the Add to Cart button is visible
        wait.until(ExpectedConditions.visibilityOf(addToCartButton));

        // Scroll to the Add to Cart button
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);

        // Wait for a short period to ensure scrolling is complete
        Thread.sleep(2000); // Use WebDriverWait if preferred

        // Click the Add to Cart button
        Actions actions = new Actions(driver);
        actions.moveToElement(addToCartButton).click().perform();
    }
}
