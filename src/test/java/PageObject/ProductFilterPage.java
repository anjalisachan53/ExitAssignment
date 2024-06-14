package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductFilterPage {

	WebDriver driver;

    public ProductFilterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    
    @FindBy(xpath = "//div[@class='_6i1qKy' and text()='SAMSUNG']")
    WebElement samsungFilter;

    
    public void clickSamsungFilter() {
        samsungFilter.click();
    }
	
}
