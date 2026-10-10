package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage {

    @FindBy(className = "inventory_details_name")
    private WebElement productName;

    public ProductDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductName() {
        return productName;
    }
}