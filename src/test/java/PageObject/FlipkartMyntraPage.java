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

public class FlipkartMyntraPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Constructor
    public FlipkartMyntraPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Locate the Myntra link
    @FindBy(xpath = "//a[@href='https://www.myntra.com/' and @aria-label='Myntra']")
    WebElement myntraLink;

    // Scroll to the bottom of the page
    public void scrollToBottom() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        wait.until(ExpectedConditions.visibilityOf(myntraLink));
    }

    // Click on the Myntra link
    public void clickMyntraLink() {
        wait.until(ExpectedConditions.elementToBeClickable(myntraLink));
        myntraLink.click();
    }

    // Check if the current URL is the Myntra page
    public boolean isOnMyntraPage() {
        String currentURL = driver.getCurrentUrl();
        return currentURL.equals("https://www.myntra.com/");
    }
}
