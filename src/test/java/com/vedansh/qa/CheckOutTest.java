
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckOutTest extends BaseTest {

    @Test
    public void testCompleteCheckout() {

        // Step 1: Login
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        wait.until(ExpectedConditions.urlContains("inventory.html"));

        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertEquals(
                inventoryPage.getPageTitle(),
                "Products",
                "Products page should be displayed after login"
        );

        // Step 2: Add a product to the cart
        inventoryPage.addBackpackToCart();
        logStep("Added Sauce Labs Backpack to the cart");

        // Step 3: Open the cart
        CartPage cartPage = new CartPage(driver);
        cartPage.openCart();

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                1,
                "Expected exactly one product in the cart"
        );

        // Step 4: Proceed to checkout
        wait.until(
                ExpectedConditions.elementToBeClickable(By.id("checkout"))
        ).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-one.html")
        );

        // Step 5: Enter customer details
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.enterCheckoutDetails(
                "Vedansh",
                "Dixit",
                "160001"
        );

        // Step 6: Verify the entered values
        Assert.assertEquals(
                checkoutPage.getFirstName(),
                "Vedansh",
                "First name was not entered correctly"
        );

        Assert.assertEquals(
                checkoutPage.getLastName(),
                "Dixit",
                "Last name was not entered correctly"
        );

        Assert.assertEquals(
                checkoutPage.getPostalCode(),
                "160001",
                "Postal code was not entered correctly"
        );

        // Step 7: Continue to checkout overview
        checkoutPage.clickContinue();

        wait.until(
                ExpectedConditions.urlContains("checkout-step-two.html")
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
                ExpectedConditions.elementToBeClickable(By.id("finish"))
        ).click();

        wait.until(
                ExpectedConditions.urlContains("checkout-complete.html")
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
