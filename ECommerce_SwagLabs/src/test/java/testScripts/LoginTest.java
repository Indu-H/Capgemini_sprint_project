
package testScripts;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.DataProviderUtility;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class LoginTest extends BaseClass {

    @Test(groups = "smoke",dataProvider = "validLoginData",dataProviderClass = DataProviderUtility.class)
    public void verifyValidLogin(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        Reporter.log("Login credentials entered", true);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should appear after successful login");

        Reporter.log("SMOKE TEST PASSED: Valid login opens the Product Catalog successfully",true);
    }
}