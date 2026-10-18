
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By inventoryItems =
            By.className("inventory_item");

    private final By sortDropdown =
            By.className("product_sort_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public int getProductCount() {
        waitUtils.waitForVisibility(
                By.className("inventory_item")
        );

        return driver.findElements(inventoryItems).size();
    }

    public void sortByPriceLowToHigh() {
        WebElement dropdown =
                waitUtils.waitForVisibility(sortDropdown);

        Select select = new Select(dropdown);
        select.selectByValue("lohi");
    }
    public void sortByPriceHighToLow() {
        WebElement dropdown =
                waitUtils.waitForVisibility(sortDropdown);

        Select select = new Select(dropdown);
        select.selectByValue("hilo");
    }
    public List<Double> getProductPrices() {
        List<WebElement> priceElements =
                driver.findElements(
                        By.className("inventory_item_price")
                );

        List<Double> prices = new ArrayList<>();

        for (WebElement element : priceElements) {
            String priceText = element.getText()
                    .replace("$", "")
                    .trim();

            prices.add(Double.parseDouble(priceText));
        }

        return prices;
    }
}
