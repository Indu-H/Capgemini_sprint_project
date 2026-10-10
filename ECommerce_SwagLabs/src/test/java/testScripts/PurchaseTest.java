
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
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class PurchaseTest extends BaseClass {

    @Test(groups = "functional",dataProvider = "purchaseData",dataProviderClass = DataProviderUtility.class)
    public void verifyUserCanCompletePurchase(String username,String password,String firstName,String lastName,String postalCode) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should appear after login");

        Reporter.log("Login successful and Product Catalog displayed", true);

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getBackpackAddToCartButton())).click();

        Reporter.log("Sauce Labs Backpack added to cart", true);

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getCartLink())).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        Assert.assertEquals(cartPage.getProductName(),"Sauce Labs Backpack","Selected product should appear in cart");

        Reporter.log("Cart product verified successfully", true);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        checkoutPage.enterCustomerDetails(firstName, lastName, postalCode);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("continue"))).click();

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-two.html"),"Checkout overview page should be displayed");

        Reporter.log("Checkout overview verified successfully", true);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("finish"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));

        Assert.assertEquals(checkoutPage.getConfirmationMessage(),"Thank you for your order!","Purchase confirmation should be displayed");

        Reporter.log("FUNCTIONAL TEST PASSED: Login -> Cart -> Checkout -> Purchase",true);
    }
}