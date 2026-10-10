
package genericUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class ScreenshotUtility {

    public String captureScreenshot(
            WebDriver driver, String fileName) throws IOException {

        Path folderPath = Path.of("screenshots", "negative");
        Files.createDirectories(folderPath);

        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        Path destinationPath = folderPath.resolve(fileName + ".png");

        Files.copy(sourceFile.toPath(),destinationPath,StandardCopyOption.REPLACE_EXISTING);

        String screenshotPath = destinationPath.toAbsolutePath().toString();

        Reporter.log("Screenshot saved: " + screenshotPath, true);

        return screenshotPath;
    }
}

