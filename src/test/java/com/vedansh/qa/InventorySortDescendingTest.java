
package com.vedansh.qa;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class InventorySortDescendingTest extends BaseTest {

    @Test
    public void sortProductsByPriceHighToLowTest() {

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

        // Step 2: Create inventory page object
        InventoryPage inventoryPage = new InventoryPage(driver);

        Assert.assertTrue(
                inventoryPage.getProductCount() > 0,
                "Inventory should contain products."
        );

        // Step 3: Sort prices from high to low
        inventoryPage.sortByPriceHighToLow();

        // Step 4: Verify descending price order
        List<Double> prices = inventoryPage.getProductPrices();

        Assert.assertTrue(
                prices.size() > 1,
                "At least two product prices should be available."
        );

        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(
                    prices.get(i) >= prices.get(i + 1),
                    "Products are not sorted by descending price: " + prices
            );
        }

        System.out.println(
                "Descending price sorting passed. Prices: " + prices
        );
    }
}
