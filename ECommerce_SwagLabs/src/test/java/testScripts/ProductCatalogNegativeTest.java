
package testScripts;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.ScreenshotUtility;
import objRepo.LoginPage;

public class ProductCatalogNegativeTest extends BaseClass {

    @Test(groups = "negative")
    public void verifyUnauthorizedUserCannotAccessProductCatalog()throws Exception {

        LoginPage loginPage = new LoginPage(driver);
        ScreenshotUtility screenshotUtility = new ScreenshotUtility();

        Reporter.log("Attempting to open Product Catalog without logging in", true);

        driver.get("https://www.saucedemo.com/inventory.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(d ->d.getCurrentUrl().equals("https://www.saucedemo.com/")|| d.getCurrentUrl().equals("https://www.saucedemo.com"));

        wait.until(d -> loginPage.getUsernameTextField().isDisplayed());

        screenshotUtility.captureScreenshot(driver, "02_Unauthorized_Catalog_Access");

        String actualUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualUrl.equals("https://www.saucedemo.com/")|| actualUrl.equals("https://www.saucedemo.com"),"Unauthorized user should be redirected to the login page");

        Reporter.log("NEGATIVE TEST PASSED: Unauthorized access redirected to Login page", true);
    }
}

