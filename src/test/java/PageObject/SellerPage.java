package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SellerPage {
    private WebDriver driver;

    // Constructor
    public SellerPage(WebDriver driver) {
        this.driver = driver;
    }

    // Click on the "Become a Seller" link
    public void clickBecomeASellerLink() {
        WebElement becomeASellerLink = driver.findElement(By.xpath("//a[@title='Become a Seller']"));
        becomeASellerLink.click();
    }

    // Check if the "Start Selling" button is present
    public boolean isStartSellingButtonPresent() {
        WebElement startSellingButton = driver.findElement(By.xpath("//button[@data-testid='button' and text()='Start Selling']"));
        return startSellingButton.isDisplayed();
    }
}
