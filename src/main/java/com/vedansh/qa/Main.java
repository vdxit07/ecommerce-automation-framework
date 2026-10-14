package com.vedansh.qa;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.saucedemo.com/");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login("standard_user", "secret_sauce");

            WebDriverWait wait =
                    new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement productsTitle = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.className("title")
                    )
            );

            String actualTitle = productsTitle.getText();
            System.out.println("Page heading: " + actualTitle);

            if (actualTitle.equals("Products")) {
                System.out.println("LOGIN TEST PASSED");
            } else {
                System.out.println("LOGIN TEST FAILED");
            }

        } finally {
            driver.quit();
        }
    }
}
