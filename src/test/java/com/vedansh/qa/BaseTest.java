package com.vedansh.qa;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class BaseTest {

    protected WebDriver driver;
    protected ExtentTest extentTest;

    private static final ExtentReports extent =
            ExtentManager.getInstance();

    protected String takeScreenshot(String testName) {
        try {
            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            Path screenshotDirectory =
                    Path.of("target", "screenshots");

            Files.createDirectories(screenshotDirectory);

            Path destination = screenshotDirectory.resolve(
                    testName + ".png"
            );

            Files.copy(
                    screenshot.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            return destination.toString();

        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to save screenshot", e
            );
        }
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        extentTest = extent.createTest(
                result.getMethod().getMethodName()
        );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                extentTest.pass("Test passed");

            } else if (result.getStatus() == ITestResult.FAILURE) {
                extentTest.fail("Test failed");

                if (result.getThrowable() != null) {
                    extentTest.fail(result.getThrowable());
                }

                if (driver != null) {
                    try {
                        String screenshotPath = takeScreenshot(
                                result.getMethod().getMethodName()
                        );

                        extentTest.addScreenCaptureFromPath(
                                Path.of(screenshotPath)
                                        .toAbsolutePath()
                                        .toString()
                        );

                    } catch (Exception e) {
                        extentTest.warning(
                                "Could not attach failure screenshot: "
                                        + e.getMessage()
                        );
                    }
                }

            } else if (result.getStatus() == ITestResult.SKIP) {
                extentTest.skip("Test skipped");
            }

        } finally {
            if (driver != null) {
                driver.quit();
            }

            extent.flush();
        }
    }
}