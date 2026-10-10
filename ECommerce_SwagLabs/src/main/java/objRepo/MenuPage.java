package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuPage {

    @FindBy(id = "react-burger-menu-btn")
    private WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLink;

    @FindBy(id = "reset_sidebar_link")
    private WebElement resetAppStateLink;

    @FindBy(id = "react-burger-cross-btn")
    private WebElement closeMenuButton;

    public MenuPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void openMenu() {
        menuButton.click();
    }

    public void clickLogout() {
        logoutLink.click();
    }

    public void clickResetAppState() {
        resetAppStateLink.click();
    }

    public void closeMenu() {
        closeMenuButton.click();
    }

}
