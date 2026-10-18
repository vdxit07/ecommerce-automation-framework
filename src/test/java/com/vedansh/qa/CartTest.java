
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

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        String badgeCount = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        ).getText();

        Assert.assertEquals(badgeCount, "1");

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("cart_item")
                )
        );

        Assert.assertEquals(cartPage.getCartItemCount(), 1);
        Assert.assertEquals(cartPage.getCartBadgeCount(), "1");

        System.out.println("Add product test passed.");
    }

    @Test
    public void removeProductFromCartTest() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        wait.until(
                ExpectedConditions.urlContains("inventory.html")
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        ).click();

        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        wait.until(
                ExpectedConditions.urlContains("cart.html")
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("cart_item")
                )
        );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain the backpack before removal."
        );

        // Remove the backpack using the CartPage method.
        cartPage.removeBackpackFromCart();

        // Wait until the cart item disappears.
        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        By.className("cart_item")
                )
        );

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                0,
                "Cart should be empty after removing the backpack."
        );

        Assert.assertTrue(
                driver.findElements(
                        By.className("shopping_cart_badge")
                ).isEmpty(),
                "Cart badge should disappear when the cart is empty."
        );

        System.out.println("Remove product test passed.");
    }
}
