
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
import objRepo.LoginPage;
import objRepo.MenuPage;
import objRepo.ProductPage;

public class ResetAppStateTest extends BaseClass {

    @Test(groups = "functional",dataProvider = "validLoginData",dataProviderClass = DataProviderUtility.class)
    public void verifyResetAppStateClearsCart(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        LoginPage loginPage = new LoginPage(driver);
        MenuPage menuPage = new MenuPage(driver);
        ProductPage productPage = new ProductPage(driver);

        Reporter.log("Entering login credentials", true);

        loginPage.getUsernameTextField().sendKeys(username);
        loginPage.getPasswordTextField().sendKeys(password);
        loginPage.getLoginButton().click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(), "Products");
        Reporter.log("Login successful", true);

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(),"1");

        Reporter.log("Product added to cart", true);

        menuPage.openMenu();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("reset_sidebar_link")));

        menuPage.clickResetAppState();
        Reporter.log("Clicked Reset App State", true);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("shopping_cart_badge")));

        driver.get("https://www.saucedemo.com/inventory.html");

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(driver.findElements(By.className("shopping_cart_badge")).size(),0,"Cart should be empty after Reset App State");

        String buttonText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-sauce-labs-backpack"))).getText();

        Assert.assertEquals(buttonText, "Add to cart");

        Reporter.log("FUNCTIONAL TEST PASSED: Reset App State cleared the cart", true);
    }
}

