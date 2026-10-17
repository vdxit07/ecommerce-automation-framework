
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {

    @Test
    public void addProductToCartTest() {

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

        // Step 2: Add the product
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        // Step 3: Wait until the cart badge confirms the product was added
        String badgeCount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        ).getText();

        Assert.assertEquals(
                badgeCount,
                "1",
                "Cart badge should display 1 after adding the product."
        );

        // Step 4: Open the cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );

        // Step 5: Wait for the cart item and verify the count
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("cart_item")
                )
        );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain one product."
        );

        Assert.assertEquals(
                cartPage.getCartBadgeCount(),
                "1",
                "Cart badge should display 1."
        );

        System.out.println(
                "Cart test passed: one product is present."
        );
    }
}
