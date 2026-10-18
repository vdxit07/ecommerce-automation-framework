
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {

    @Test
    public void testCompleteCheckout() {

        // Step 1: Login
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("user-name")
                )
        ).sendKeys("standard_user");

        driver.findElement(By.id("password"))
                .sendKeys("secret_sauce");

        driver.findElement(By.id("login-button"))
                .click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("inventory_list")
                )
        );

        // Step 2: Add a product to the cart
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        // Step 3: Open the cart
        driver.findElement(By.className("shopping_cart_link"))
                .click();

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("checkout")
                )
        );

        // Step 4: Proceed to checkout
        WebElement checkoutButton =
                driver.findElement(By.id("checkout"));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].click();",
                checkoutButton
        );

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-one.html"
                )
        );

        // Step 5: Enter customer details
        WebElement firstName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("first-name")
                )
        );

        firstName.sendKeys("Vedansh");

        WebElement lastName =
                driver.findElement(By.id("last-name"));
        lastName.sendKeys("Dixit");

        WebElement postalCode =
                driver.findElement(By.id("postal-code"));
        postalCode.sendKeys("160001");

        // Step 6: Verify the entered values
        Assert.assertEquals(
                firstName.getDomProperty("value"),
                "Vedansh",
                "First name was not entered correctly"
        );

        Assert.assertEquals(
                lastName.getDomProperty("value"),
                "Dixit",
                "Last name was not entered correctly"
        );

        Assert.assertEquals(
                postalCode.getDomProperty("value"),
                "160001",
                "Postal code was not entered correctly"
        );

        // Step 7: Continue to checkout overview
        driver.findElement(By.id("continue")).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-two.html"
                )
        );

        // Step 8: Verify the order overview
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("summary_info")
                )
        );

        Assert.assertEquals(
                driver.findElements(By.className("cart_item")).size(),
                1,
                "Expected exactly one product in the order overview"
        );

        Assert.assertTrue(
                driver.findElement(
                        By.className("inventory_item_name")
                ).getText().contains("Sauce Labs Backpack"),
                "Expected Sauce Labs Backpack in the order overview"
        );

        // Step 9: Finish the order
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("finish")
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-complete.html"
                )
        );

        // Step 10: Verify successful order completion
        String confirmationMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        ).getText();

        Assert.assertEquals(
                confirmationMessage,
                "Thank you for your order!",
                "Order confirmation message did not match"
        );

        logStep("Checkout completed and confirmation verified");
    }
}
