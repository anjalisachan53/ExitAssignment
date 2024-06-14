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

public class FlipkartSocialMediaPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public FlipkartSocialMediaPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locate the YouTube icon
    @FindBy(xpath = "//img[@src='https://static-assets-web.flixcart.com/batman-returns/batman-returns/p/images/YoutubeLogo-8425c4.svg' and @alt='YouTube']")
    WebElement youtubeIcon;

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(youtubeIcon));
    }

    // Click on the YouTube icon
    public void clickYoutubeIcon() {
        wait.until(ExpectedConditions.elementToBeClickable(youtubeIcon));
        youtubeIcon.click();
    }

    // Check if the current URL is the Flipkart YouTube page
    public boolean isOnYoutubePage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://www.youtube.com/flipkart");
    }
}
