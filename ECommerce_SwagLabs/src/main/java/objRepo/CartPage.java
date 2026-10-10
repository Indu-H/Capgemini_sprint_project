package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

    @FindBy(className = "inventory_item_name")
    private WebElement productName;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getProductName() {
        return productName.getText();
    }

    public void clickCheckout() {
        checkoutButton.click();
    }
}
