package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotificationPreferencePage {
    WebDriver driver;

    public NotificationPreferencePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locate the three dots icon
    @FindBy(xpath = "//div[@class='H6-NpN']/a")
    WebElement threeDotsIcon;

    // Locate the "Notification Preferences" option
    @FindBy(xpath = "//li[contains(text(),'Notification Preferences')]")
    WebElement notificationPreferencesOption;

    // Method to click on the three dots icon
    public void clickThreeDotsIcon() {
        threeDotsIcon.click();
    }

    // Method to click on the "Notification Preferences" option
    public void clickNotificationPreferencesOption() {
        notificationPreferencesOption.click();
    }
}
