package com.example;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.time.Duration;

public class UserLoginTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAHUL RANJAN DAS\\Softwares\\chromedriver-win64\\chromedriver.exe");
    }

    @BeforeMethod
    public void setupTest() {
        ChromeOptions options = new ChromeOptions();
        // Uncomment to run in headless mode if desired:
        // options.addArguments("--headless");
        this.driver = new ChromeDriver(options);
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void loginAndTakeScreenshot() throws Exception {
        driver.get("https://www.saucedemo.com");

        // Enter username
        WebElement userField = driver.findElement(By.id("user-name"));
        userField.sendKeys("standard_user");

        // Enter password
        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys("secret_sauce");

        // Click Login
        WebElement loginBtn = driver.findElement(By.id("login-button"));
        loginBtn.click();

        // Wait for products page to load by checking for a known element
        driver.findElement(By.className("inventory_list"));

        // Take screenshot
        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

        String targetDir = "C:\\Users\\RAHUL RANJAN DAS\\Pictures\\Screenshots";
        Files.createDirectories(Paths.get(targetDir));
        Path screenshotPath = Paths.get(targetDir, "saucedemo_login.png");
        Files.write(screenshotPath, screenshot);
        System.out.println("Screenshot saved to: " + screenshotPath.toAbsolutePath());
    }
}
