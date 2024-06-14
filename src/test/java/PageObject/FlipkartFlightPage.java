package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class FlipkartFlightPage {
    private WebDriver driver;

    // Constructor
    public FlipkartFlightPage(WebDriver driver) {
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

    // Check if the current URL is the Flights page
    public boolean isOnFlightsPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("flipkart.com/travel/flights?otracker=nmenu_Flights");
    }
}
