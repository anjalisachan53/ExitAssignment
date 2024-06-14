package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FlipkartPlusPage {
    private WebDriver driver;

    // Constructor
    public FlipkartPlusPage(WebDriver driver) {
        this.driver = driver;
    }

    // Hover over the "Login" element
    public void hoverOverLogin() {
        WebElement loginElement = driver.findElement(By.xpath("//div[@class='H6-NpN _3N4_BX']/a[@title='Login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(loginElement).perform();
    }

    // Click on the "Flipkart Plus Zone" link
    public void clickFlipkartPlusZoneLink() {
        WebElement flipkartPlusZoneLink = driver.findElement(By.xpath("//li[contains(., 'Flipkart Plus Zone')]"));
        flipkartPlusZoneLink.click();
    }

    // Check if the current URL is the Flipkart Plus page
    public boolean isOnFlipkartPlusPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("flipkart.com/plus");
    }
}
