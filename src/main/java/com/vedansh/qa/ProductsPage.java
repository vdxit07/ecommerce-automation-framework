package com.vedansh.qa;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class ProductsPage {
    private final WebDriver driver;
    private final By pageTitle = By.className("title");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }
}
