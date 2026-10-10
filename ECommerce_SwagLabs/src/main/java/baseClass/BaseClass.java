
package baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.BrowserUtility;
import genericUtility.WebDriverUtility;

public class BaseClass {

    protected WebDriver driver;
    protected WebDriverUtility webDriverUtility;
    protected WebDriverWait wait;
    protected Properties properties;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("") String browserFromXml)throws IOException {

        properties = new Properties();

        FileInputStream fileInputStream = new FileInputStream("./src/test/resources/DDT/BaseClass.properties");

        try {
            properties.load(fileInputStream);
        } 
        finally {
            fileInputStream.close();
        }

        String browser = browserFromXml.isEmpty()? properties.getProperty("browser", "chrome"): browserFromXml;

        String url = properties.getProperty("url");

        if (url == null || url.trim().isEmpty()) {
        	
            throw new IllegalArgumentException("URL is missing in BaseClass.properties");
        }

        BrowserUtility browserUtility = new BrowserUtility();

        if (browser.equalsIgnoreCase("chrome")) {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-save-password-bubble");
            options.setExperimentalOption("prefs",java.util.Map.of("credentials_enable_service", false,"profile.password_manager_enabled", false,"profile.password_manager_leak_detection", false));

            driver = new org.openqa.selenium.chrome.ChromeDriver(options);

        } else {
        	
            driver = browserUtility.launchBrowser(browser);
        }

        webDriverUtility = new WebDriverUtility();

        webDriverUtility.maximizeWindow(driver);
        webDriverUtility.implicitWait(driver, 10);

        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}

