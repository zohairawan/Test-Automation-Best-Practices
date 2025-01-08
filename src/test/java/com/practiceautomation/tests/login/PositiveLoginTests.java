package com.practiceautomation.tests.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class PositiveLoginTests {

    public void testLoginFunctionality() {
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
    }
}
