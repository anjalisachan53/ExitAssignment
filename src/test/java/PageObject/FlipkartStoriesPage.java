package PageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartStoriesPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public FlipkartStoriesPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locate the Flipkart Stories link
    @FindBy(xpath = "//a[@href='http://stories.flipkart.com/' and @aria-label='Flipkart Stories']")
    WebElement flipkartStoriesLink;

    // Locate the Subscribe button
    @FindBy(xpath = "//div[@class='ast-custom-button' and text()='Subscribe']")
    WebElement subscribeButton;

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(flipkartStoriesLink));
    }

    // Click on the Flipkart Stories link
    public void clickFlipkartStoriesLink() {
        wait.until(ExpectedConditions.elementToBeClickable(flipkartStoriesLink));
        flipkartStoriesLink.click();
    }

    // Click on the Subscribe button
    public void clickSubscribeButton() {
        wait.until(ExpectedConditions.elementToBeClickable(subscribeButton));
        subscribeButton.click();
    }

    // Check if the current URL is the Subscribe page
    public boolean isOnSubscribePage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://stories.flipkart.com/subscribe/");
    }
}
