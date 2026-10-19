
package com.vedansh.qa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class DriverFactory {

    private DriverFactory() {
        // Prevent creating DriverFactory objects.
    }

    public static WebDriver createDriver() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--guest");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--headless=new");
        options.addArguments("--disable-save-password-bubble");

        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        return new ChromeDriver(options);
    }
}
