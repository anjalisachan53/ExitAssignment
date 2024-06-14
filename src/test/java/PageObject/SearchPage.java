package PageObject;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the search input field
    @FindBy(xpath = "//input[@class='Pke_EE' and @name='q']")
    WebElement searchInput;


    // Method to search for a product
    public void searchProduct(String productName) {
        searchInput.sendKeys(productName);
        searchInput.sendKeys(Keys.RETURN);
    }
}