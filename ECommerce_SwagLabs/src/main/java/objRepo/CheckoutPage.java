package objRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    public CheckoutPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterCustomerDetails(String firstNameValue, String lastNameValue, String postalCodeValue) {

        firstName.clear();
        firstName.sendKeys(firstNameValue);

        lastName.clear();
        lastName.sendKeys(lastNameValue);

        postalCode.clear();
        postalCode.sendKeys(postalCodeValue);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickFinish() {
        finishButton.click();
    }

    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }
}