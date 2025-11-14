package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UserLoginTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set the ChromeDriver executable path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\RAHUL RANJAN DAS\\Softwares\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testUserLoginAndScreenshot() throws IOException {
        driver.get("https://www.saucedemo.com");
        // Enter username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // Enter password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        // Click login
        driver.findElement(By.id("login-button")).click();

        // Optionally, verify successful login
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed or inventory page not reached.");

        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destFile = "C:\\Users\\RAHUL RANJAN DAS\\Pictures\\Screenshots\\saucedemo_login.png";
        Files.createDirectories(Paths.get("C:\\Users\\RAHUL RANJAN DAS\\Pictures\\Screenshots"));
        Files.copy(srcFile.toPath(), Paths.get(destFile), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void testProblemUserLoginAndScreenshot() throws IOException {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("problem_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Login failed or inventory page not reached for problem_user.");

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destFile = "C:\\Users\\RAHUL RANJAN DAS\\Pictures\\Screenshots\\saucedemo_problemuser.png";
        Files.createDirectories(Paths.get("C:\\Users\\RAHUL RANJAN DAS\\Pictures\\Screenshots"));
        Files.copy(srcFile.toPath(), Paths.get(destFile), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
