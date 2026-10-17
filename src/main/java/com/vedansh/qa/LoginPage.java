package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void login(String username, String password) {
        waitUtils.waitForVisibility(usernameField).sendKeys(username);
        waitUtils.waitForVisibility(passwordField).sendKeys(password);
        waitUtils.waitForClickability(loginButton).click();

    }
    public String getErrorMessage() {
        return waitUtils.waitForVisibility(
                By.cssSelector("[data-test='error']")
        ).getText();
    }


}