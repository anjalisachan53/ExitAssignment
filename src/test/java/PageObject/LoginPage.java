package PageObject;


import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locate the login link
    @FindBy(xpath = "//a[@class='_1jKL3b' and @href='/account/login?ret=/']")
    WebElement loginLink;
    
    
    @FindBy(css = "input[type='text'][autocomplete='off'][class='r4vIwl BV+Dqf']")
    WebElement email;
    
   

    // Method to click the login link
    public void clickLoginLink() {
        loginLink.click();
    }
    
    public void enterEmail(String userEmail) {
        //wait.until(ExpectedConditions.visibilityOf(email)); // Wait until email field is visible
    	//wait.until(ExpectedConditions.visibilityOf(email));
    	email.sendKeys(userEmail);
        //email.sendKeys(Keys.ENTER); // Simulate hitting the Enter key
    }

   
    
}
