package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PincodeChangePage {
    WebDriver driver;

    public PincodeChangePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the pincode input field
    @FindBy(id = "pincodeInputId")
    WebElement pincodeInput;

    // Locate the "Change" button
    @FindBy(xpath = "//span[contains(@class, 'i40dM4')]")
    WebElement changeButton;

    // Locate the error message for invalid pincode
    @FindBy(xpath = "//div[contains(@class, 'nyRpc8')]")
    WebElement errorMessage;

    // Method to enter a new pincode
    public void enterPincode(String pincode) {
        pincodeInput.clear();
        pincodeInput.sendKeys(pincode);
    }

    // Method to click the "Change" button
    public void clickChangeButton() {
        changeButton.click();
    }

    // Method to get the error message text for invalid pincode
    public String getErrorMessageText() {
        return errorMessage.getText();
    }
}
