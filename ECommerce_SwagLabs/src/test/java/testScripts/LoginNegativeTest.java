package testScripts;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import genericUtility.DataProviderUtility;
import genericUtility.ScreenshotUtility;
import objRepo.LoginPage;

public class LoginNegativeTest extends BaseClass {

    @Test(dataProvider = "negativeLoginData", dataProviderClass = DataProviderUtility.class, groups = "negative")
    public void verifyLoginWithInvalidCredentials(String username, String password) throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        LoginPage loginPage = new LoginPage(driver);
        ScreenshotUtility screenshotUtility = new ScreenshotUtility();

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameTextField()));

        Reporter.log("Attempting login with username: " + username, true);

        loginPage.getUsernameTextField().clear();
        loginPage.getUsernameTextField().sendKeys(username);

        loginPage.getPasswordTextField().clear();
        loginPage.getPasswordTextField().sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

        wait.until(ExpectedConditions.visibilityOf(loginPage.getLoginErrorMessage()));

        String actualError = loginPage.getLoginErrorMessage().getText();

        if (username.equals("locked_out_user")) {

            Assert.assertEquals(actualError,"Epic sadface: Sorry, this user has been locked out.","Expected locked-out user error should appear");

            screenshotUtility.captureScreenshot(driver, "02_Locked_Out_User");

            Reporter.log("NEGATIVE TEST PASSED: Locked-out user was prevented from logging in", true);

        } else {

            Assert.assertEquals(actualError,"Epic sadface: Username and password do not match any user in this service","Expected invalid credentials error should appear");

            screenshotUtility.captureScreenshot(driver, "01_Invalid_Login");

            Reporter.log("NEGATIVE TEST PASSED: Invalid credentials rejected with the expected error", true);
        }
    }
}