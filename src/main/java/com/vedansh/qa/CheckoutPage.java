package com.vedansh.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    public void enterCheckoutDetails(
            String firstName,
            String lastName,
            String postalCode) {

        WebElement firstNameInput = wait.until(
                ExpectedConditions.elementToBeClickable(firstNameField)
        );

        WebElement lastNameInput = wait.until(
                ExpectedConditions.elementToBeClickable(lastNameField)
        );

        WebElement postalCodeInput = wait.until(
                ExpectedConditions.elementToBeClickable(postalCodeField)
        );

        Actions actions = new Actions(driver);

        actions.click(firstNameInput)
                .sendKeys(firstName)
                .click(lastNameInput)
                .sendKeys(lastName)
                .click(postalCodeInput)
                .sendKeys(postalCode)
                .perform();
    }

    public String getFirstName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).getDomProperty("value");
    }

    public String getLastName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameField)
        ).getDomProperty("value");
    }

    public String getPostalCode() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCodeField)
        ).getDomProperty("value");
    }

    public void clickContinue() {
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }
}