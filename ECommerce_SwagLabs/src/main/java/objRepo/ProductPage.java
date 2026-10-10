package objRepo;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {

    @FindBy(className = "title")
    private WebElement productsTitle;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> productPrices;

    @FindBy(className = "inventory_item_name")
    private WebElement backpackProduct;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public ProductPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getProductsTitle() {
        return productsTitle;
    }

    public WebElement getSortDropdown() {
        return sortDropdown;
    }

    public List<WebElement> getProductPrices() {
        return productPrices;
    }

    public WebElement getBackpackProduct() {
        return backpackProduct;
    }

    public WebElement getBackpackAddToCartButton() {
        return backpackAddToCartButton;
    }

    public WebElement getCartLink() {
        return cartLink;
    }

    public void selectSortOption(String option) {
        Select select = new Select(sortDropdown);
        select.selectByVisibleText(option);
    }
}