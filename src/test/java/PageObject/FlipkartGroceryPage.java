package PageObject;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartGroceryPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public FlipkartGroceryPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locate the Grocery element
    @FindBy(xpath = "//div[@class='YBLJE4']")
    WebElement groceryElement;

    // Locate the Verify Delivery Pincode element
    @FindBy(xpath = "//div[@class='b3YJls' and text()='Verify Delivery Pincode']")
    WebElement verifyPincodeElement;

    // Locate the Deals of the Day element
    @FindBy(xpath = "//div[contains(text(), 'Deals of the Day')]")
    WebElement dealsOfTheDayElement;

    // Click on the Grocery element
    public void clickGrocery() {
        wait.until(ExpectedConditions.visibilityOf(groceryElement));
        groceryElement.click();
    }

    // Check if the Verify Delivery Pincode element is present
    public boolean isVerifyPincodePresent() {
        wait.until(ExpectedConditions.visibilityOf(verifyPincodeElement));
        return verifyPincodeElement.isDisplayed();
    }

    // Check if the Deals of the Day element is present
    public boolean isDealsOfTheDayPresent() {
        wait.until(ExpectedConditions.visibilityOf(dealsOfTheDayElement));
        return dealsOfTheDayElement.isDisplayed();
    }
}
