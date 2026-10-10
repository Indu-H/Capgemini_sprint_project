package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "user-name")
    private WebElement usernameTextField;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement loginErrorMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernameTextField() {
        return usernameTextField;
    }

    public WebElement getPasswordTextField() {
        return passwordTextField;
    }

    public WebElement getLoginButton() {
        return loginButton;
    }

    public WebElement getLoginErrorMessage() {
        return loginErrorMessage;
    }
}