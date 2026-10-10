
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
import objRepo.ProductDetailsPage;
import objRepo.ProductPage;

public class ProductCatalogTest extends BaseClass {

    @Test(groups = "smoke")
    public void verifyProductCatalogOpensSuccessfully() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().sendKeys("standard_user");
        loginPage.getPasswordTextField().sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should be displayed");

        Reporter.log("SMOKE TEST PASSED: Product Catalog opens successfully",true);
    }

    @Test(groups = "functional")
    public void verifyProductPriceSorting() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().sendKeys("standard_user");
        loginPage.getPasswordTextField().sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        wait.until(ExpectedConditions.elementToBeClickable(By.className("product_sort_container"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".product_sort_container option[value='lohi']"))).click();

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("inventory_item_price")));

        double firstPrice = Double.parseDouble(driver.findElements(By.className("inventory_item_price")).get(0).getText().replace("$", ""));

        double secondPrice = Double.parseDouble(driver.findElements(By.className("inventory_item_price")).get(1).getText().replace("$", ""));

        Assert.assertTrue(firstPrice <= secondPrice,"Products should be sorted from low price to high price");

        Reporter.log("FUNCTIONAL TEST PASSED: Products are sorted by price from low to high",true);
    }

    @Test(groups = "integration")
    public void verifyLoginToProductCatalogIntegration() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().sendKeys("standard_user");
        loginPage.getPasswordTextField().sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Login should navigate to the Product Catalog");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),"Inventory URL should be displayed after login");

        Reporter.log("INTEGRATION TEST PASSED: Login successfully navigates to Product Catalog",true);
    }

    @Test(groups = "integration")
    public void verifyProductCatalogToCartIntegration() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().sendKeys("standard_user");
        loginPage.getPasswordTextField().sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getBackpackAddToCartButton())).click();

        wait.until(ExpectedConditions.elementToBeClickable(productPage.getCartLink())).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_item_name")));

        Assert.assertTrue(driver.findElement(By.className("inventory_item_name")).getText().contains("Backpack"),"Added product should appear in the cart");

        Reporter.log("INTEGRATION TEST PASSED: Product Catalog successfully adds a product to Cart",true);
    }

    @Test(groups = "system")
    public void verifyLoginCatalogAndProductDetails() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        loginPage.getUsernameTextField().sendKeys("standard_user");
        loginPage.getPasswordTextField().sendKeys("secret_sauce");

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(productPage.getProductsTitle()));

        Assert.assertEquals(productPage.getProductsTitle().getText(),"Products","Product Catalog should be displayed");

        wait.until(ExpectedConditions.elementToBeClickable(By.id("item_4_title_link"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("inventory_details_name")));

        Assert.assertTrue(productDetailsPage.getProductName().isDisplayed(),"Product details should be displayed");

        Reporter.log("SYSTEM TEST PASSED: Login -> Product Catalog -> Product Details",true);
    }
}