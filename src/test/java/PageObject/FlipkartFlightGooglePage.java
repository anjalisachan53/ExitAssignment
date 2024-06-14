package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FlipkartFlightGooglePage {
    private WebDriver driver;

    // Constructor
    public FlipkartFlightGooglePage(WebDriver driver) {
        this.driver = driver;
    }

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

    // Click on the "Flights" link
    public void clickFlightsLink() {
        WebElement flightsLink = driver.findElement(By.xpath("//a[@class='TSD49J' and @href='/travel/flights?otracker=nmenu_Flights']"));
        flightsLink.click();
    }

    // Click on the image that leads to Google Play Store
    public void clickGooglePlayImage() {
        WebElement googlePlayImage = driver.findElement(By.xpath("//img[@class='xTaogf _3iTqAS' and @alt='header']"));
        googlePlayImage.click();
    }

    // Check if the current URL is the Google Play Store page for the Flipkart app
    public boolean isOnGooglePlayPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("play.google.com/store/apps/details?id=com.flipkart.android");
    }
}
