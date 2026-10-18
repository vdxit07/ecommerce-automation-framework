
package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private final WebDriver driver;
    private final WaitUtils waitUtils;

    private final By cartLink =
            By.className("shopping_cart_link");

    private final By cartItems =
            By.className("cart_item");

    private final By cartBadge =
            By.className("shopping_cart_badge");

    private final By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
    }

    public void openCart() {
        waitUtils.waitForClickability(cartLink).click();
    }

    public String getCartBadgeCount() {
        return waitUtils.waitForVisibility(cartBadge).getText();
    }

    public int getCartItemCount() {
        return driver.findElements(cartItems).size();
    }

    public void removeBackpackFromCart() {
        waitUtils.waitForClickability(removeBackpackButton).click();
    }
}
