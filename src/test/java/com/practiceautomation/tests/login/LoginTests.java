package com.practiceautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

    @Test(groups = {"positive", "smoke", "regression"})
    public void positiveLoginTest() {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);

        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("student");

        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("Password123");

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        String expectedUrl = "practicetestautomation.com/logged-in-successfully/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(expectedUrl));

        String expectedSuccessMessage = "successfully logged in";
        String actualSucessMessage = driver.findElement(By.tagName("strong")).getText();
        Assert.assertTrue(actualSucessMessage.contains(expectedSuccessMessage));

        WebElement logoutButton = driver.findElement(By.linkText("Log out"));
        Assert.assertTrue(logoutButton.isDisplayed());

        driver.quit();
    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(groups={"negative", "regression"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        String url = "https://practicetestautomation.com/practice-test-login/";
        driver.get(url);

        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys(username);

        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys(password);

        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();

        // Allows error message to load so Selenium doesn't skip it
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement invalidUsernameError = driver.findElement(By.id("error"));
        Assert.assertTrue(invalidUsernameError.isDisplayed());
        String actualUsernameErrorMessage = invalidUsernameError.getText();
        Assert.assertEquals(actualUsernameErrorMessage, expectedErrorMessage);

        driver.quit();
    }
}
