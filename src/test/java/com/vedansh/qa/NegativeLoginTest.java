package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class NegativeLoginTest extends BaseTest {

    private final By errorMessage = By.cssSelector(
            "[data-test='error']"
    );

    @Test
    public void invalidPasswordTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                "wrong_password"
        );

        String actualError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Username and password do not match any user in this service",
                "Expected an error for invalid credentials."
        );

        System.out.println("Invalid password test passed.");
    }

    @Test
    public void lockedOutUserTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "locked_out_user",
                "secret_sauce"
        );

        String actualError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Sorry, this user has been locked out.",
                "Expected a locked-out user error."
        );

        System.out.println("Locked-out user test passed.");
    }

    @Test
    public void emptyUsernameTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "",
                "secret_sauce"
        );

        String actualError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Username is required",
                "Expected an error when username is empty."
        );

        System.out.println("Empty username test passed.");
    }

    @Test
    public void emptyPasswordTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(
                "standard_user",
                ""
        );

        String actualError = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        errorMessage
                )
        ).getText();

        Assert.assertEquals(
                actualError,
                "Epic sadface: Password is required",
                "Expected an error when password is empty."
        );

        System.out.println("Empty password test passed.");
    }
}