
package com.vedansh.qa;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class InventorySortTest extends BaseTest {

    @Test
    public void sortProductsByPriceLowToHighTest() {

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

        // Step 2: Create the inventory page object
        InventoryPage inventoryPage = new InventoryPage(driver);

        // Step 3: Verify products are displayed
        Assert.assertTrue(
                inventoryPage.getProductCount() > 0,
                "Inventory should contain products."
        );

        // Step 4: Sort products by price, low to high
        inventoryPage.sortByPriceLowToHigh();

        // Step 5: Read prices and verify ascending order
        List<Double> prices = inventoryPage.getProductPrices();

        Assert.assertTrue(
                prices.size() > 1,
                "At least two product prices should be available."
        );

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(
                    prices.get(i) <= prices.get(i + 1),
                    "Products are not sorted by price: " + prices
            );
        }

        System.out.println(
                "Price sorting test passed. Prices: " + prices
        );
    }
}
