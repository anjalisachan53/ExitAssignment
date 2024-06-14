package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerCarePage {
	WebDriver driver;

    public CustomerCarePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the three dots icon
    @FindBy(xpath = "//div[@class='H6-NpN']/a")
    WebElement threeDotsIcon;

    // Locate the "Notification Preferences" option
    @FindBy(xpath = "//li[contains(text(),'24x7 Customer Care')]")
    WebElement customerCareOption;

    // Method to click on the three dots icon
    public void clickThreeDotsIcon() {
        threeDotsIcon.click();
    }

    // Method to click on the "Notification Preferences" option
    public void clickCustomerCareOption() {
    	customerCareOption.click();
    }

}
