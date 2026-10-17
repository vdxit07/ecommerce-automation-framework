package com.vedansh.qa;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginTest() {

        LoginPage loginPage = new LoginPage(driver);

        extentTest.info("Entering valid username and password");
        loginPage.login("standard_user", "secret_sauce");

        extentTest.info("Waiting for successful login redirect");
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.urlContains("inventory.html"));
        ProductsPage productsPage = new ProductsPage(driver);
        extentTest.info("Verifying Products page heading");
        Assert.assertEquals(
                productsPage.getPageTitle(),
                "Products",
                "Products page heading should be displayed"
        );
        extentTest.info("Verifying successful login");
        String currentUrl = driver.getCurrentUrl();

        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Assert.assertTrue(
                currentUrl.contains("inventory.html"),
                "User should be redirected to the inventory page"
        );

    }
    @Test
    public void invalidLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        extentTest.info("Attempting login with an invalid password");
        loginPage.login("standard_user", "wrong_password");

        extentTest.info("Verifying the login error message");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(
                errorMessage.contains(
                        "Username and password do not match"
                ),
                "Expected an error message for invalid credentials"
        );

    }

}