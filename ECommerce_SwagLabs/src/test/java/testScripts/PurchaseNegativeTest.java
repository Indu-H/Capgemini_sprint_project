
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
import genericUtility.ScreenshotUtility;
import objRepo.CartPage;
import objRepo.CheckoutPage;
import objRepo.LoginPage;
import objRepo.ProductPage;

public class PurchaseNegativeTest extends BaseClass {

    @Test(groups = "negative",dataProvider = "validLoginData",dataProviderClass = DataProviderUtility.class)
    public void verifyCheckoutWithEmptyCustomerDetails(String username, String password) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ScreenshotUtility screenshotUtility = new ScreenshotUtility();

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        Reporter.log("Logging in with valid credentials", true);

        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Reporter.log("Adding product to cart", true);

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getBackpackAddToCartButton())).click();

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getCartLink())).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        Reporter.log("Opening checkout without entering customer details", true);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("checkout"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name")));

        checkoutPage.clickContinue();

        String actualError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))).getText();

        Assert.assertEquals(actualError,"Error: First Name is required","Expected validation error should appear for empty customer details");

        screenshotUtility.captureScreenshot(driver, "03_Empty_Checkout_Details");

        Reporter.log("NEGATIVE TEST PASSED: Empty checkout details rejected with the expected error",true);
    }
}