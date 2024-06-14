package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RemovePage {
	
	WebDriver driver;

    public RemovePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the "Remove" button
    @FindBy(xpath = "//div[contains(@class, 'sBxzFz') and text()='Remove']")
    WebElement removeButton;
    
    @FindBy(xpath = "//div[contains(@class, 'sBxzFz fF30ZI A0MXnh') and text()='Remove']")
    WebElement confirmRemoveButton;
    
    /*
    @FindBy(className = "d+mEZR JefwG6")
    WebElement targetElement;
    */

    // Method to click the "Remove" button
    public void clickRemoveButton() {
    	//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", targetElement);
        removeButton.click();
    }
    
    public void clickConfirmRemoveButton() {
        confirmRemoveButton.click();
    }
    
    private By successMessageLocator = By.xpath("//div[contains(@class, 'eIDgeN') and contains(text(), 'Successfully removed')]");

    public By getSuccessMessageLocator() {
        return successMessageLocator;
    }

}
