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

public class FlipkartCareerPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public FlipkartCareerPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locate the Careers link
    @FindBy(xpath = "//a[@href='https://www.flipkartcareers.com/?otracker=${otracker}_navlinks' and @aria-label='Careers']")
    WebElement careersLink;

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(careersLink));
    }

    // Click on the Careers link
    public void clickCareers() {
        wait.until(ExpectedConditions.elementToBeClickable(careersLink));
        careersLink.click();
    }

    // Check if the current URL is the Flipkart Careers page
    public boolean isOnCareersPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.contains("https://www.flipkartcareers.com/?otracker=${otracker}_navlinks#!/");
    }
}
