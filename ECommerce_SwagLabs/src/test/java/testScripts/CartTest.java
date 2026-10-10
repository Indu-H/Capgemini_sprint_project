
package testScripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.DataProviderUtility;
import objRepo.CartPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class CartTest extends BaseClass {

    @Test(groups = "functional",dataProvider = "validLoginData",dataProviderClass = DataProviderUtility.class)
    public void verifyProductIsDisplayedInCart(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        Reporter.log("Entering login credentials", true);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));
        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        Reporter.log("Clicking login button", true);
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should appear after login");

        Reporter.log("Login successful and Product Catalog displayed", true);

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getBackpackAddToCartButton())).click();

        Reporter.log("Added Sauce Labs Backpack to cart", true);

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getCartLink())).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        Assert.assertEquals(cartPage.getProductName(),"Sauce Labs Backpack","The selected product should appear in the cart");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout")));

        Assert.assertTrue(driver.findElement(By.id("checkout")).isDisplayed(),"Checkout button should be displayed in the cart");

        Reporter.log("FUNCTIONAL TEST PASSED: Product displayed in Cart and Checkout button available",true);
    }
}