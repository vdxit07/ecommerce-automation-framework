
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckOutTest extends BaseTest {

    @Test
    public void completeCheckoutTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        // Step 1: Log in
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );

        // Step 2: Add a product
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        // Step 3: Open cart and proceed to checkout
        new CartPage(driver).openCart();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout")
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-one.html"
                )
        );

        // Step 4: Enter customer details
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("first-name")
                )
        ).sendKeys("Vedansh");

        driver.findElement(By.id("last-name"))
                .sendKeys("Dixit");

        driver.findElement(By.id("postal-code"))
                .sendKeys("160001");

        // Step 5: Continue to order overview
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("continue")
                )
        ).click();

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-two.html"
                )
        );

        // Step 6: Verify the order overview
        Assert.assertEquals(
                driver.findElements(By.className("cart_item")).size(),
                1,
                "Expected one product in the order overview."
        );

        Assert.assertTrue(
                driver.findElement(
                        By.className("inventory_item_name")
                ).getText().contains("Sauce Labs Backpack"),
                "Expected Sauce Labs Backpack in the order overview."
        );

        // Step 7: Finish the order
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

        // Step 8: Verify successful order completion
        String confirmationMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        ).getText();

        Assert.assertEquals(
                confirmationMessage,
                "Thank you for your order!",
                "Order confirmation message did not match."
        );

        System.out.println(
                "Order completed successfully: "
                        + confirmationMessage
        );
    }
}
