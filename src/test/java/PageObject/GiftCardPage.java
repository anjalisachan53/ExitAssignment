package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class GiftCardPage {
    private WebDriver driver;

    // Constructor
    public GiftCardPage(WebDriver driver) {
        this.driver = driver;
    }

    // Hover over the "Login" element
    public void hoverOverLogin() {
        WebElement loginElement = driver.findElement(By.xpath("//div[@class='H6-NpN _3N4_BX']/a[@title='Login']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(loginElement).perform();
    }

    // Click on the "Gift Cards" link
    public void clickGiftCardsLink() {
        WebElement giftCardsLink = driver.findElement(By.xpath("//li[contains(., 'Gift Cards')]"));
        giftCardsLink.click();
    }

    // Check if the current URL is the Gift Cards page
    public boolean isOnGiftCardPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("flipkart.com/the-gift-card-store");
    }
}
