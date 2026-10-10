
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
import objRepo.MenuPage;
import objRepo.ProductPage;

public class LogoutTest extends BaseClass {

    @Test(groups = "functional",dataProvider = "validLoginData",dataProviderClass = DataProviderUtility.class)
    public void verifyUserCanLogout(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        MenuPage menuPage = new MenuPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should appear after login");

        Reporter.log("Login successful", true);

        wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.id("react-burger-menu-btn")));

        menuPage.openMenu();

        wait.until(ExpectedConditions.elementToBeClickable(org.openqa.selenium.By.id("logout_sidebar_link")));

        menuPage.clickLogout();

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        Assert.assertTrue(loginPage.getUsernameTextField().isDisplayed(),"Login page should appear after logout");

        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.equals("https://www.saucedemo.com/")|| actualUrl.equals("https://www.saucedemo.com"),"User should return to the login page");

        Reporter.log("FUNCTIONAL TEST PASSED: User logged out and returned to Login page",true);
    }
}