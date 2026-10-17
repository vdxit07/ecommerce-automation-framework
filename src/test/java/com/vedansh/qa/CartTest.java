
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test
    public void addProductToCartTest() {

        // Log in first
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        // Add the product to the cart
        driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
        ).click();

        // Open the shopping cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        // Verify the cart contains one product
        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Cart should contain one product"
        );

        Assert.assertEquals(
                cartPage.getCartBadgeCount(),
                "1",
                "Cart badge should display 1"
        );
    }
}
