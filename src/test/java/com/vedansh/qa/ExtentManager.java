
package com.vedansh.qa;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {

            try {
                Path reportDirectory = Path.of("target", "reports");
                Files.createDirectories(reportDirectory);

                String timestamp = LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS")
                );

                String reportPath = reportDirectory
                        .resolve("extent-report-" + timestamp + ".html")
                        .toString();

                ExtentSparkReporter sparkReporter =
                        new ExtentSparkReporter(reportPath);

                sparkReporter.config().setDocumentTitle(
                        "E-commerce Automation | Test Report"
                );

                sparkReporter.config().setReportName(
                        "Selenium Test Execution Results"
                );

                sparkReporter.config().setTheme(Theme.DARK);

                extent = new ExtentReports();
                extent.attachReporter(sparkReporter);

                extent.setSystemInfo(
                        "Project",
                        "E-commerce Automation Framework"
                );

                extent.setSystemInfo(
                        "Automation Tool",
                        "Selenium WebDriver"
                );

                extent.setSystemInfo(
                        "Test Framework",
                        "TestNG"
                );

                extent.setSystemInfo(
                        "Browser",
                        "Google Chrome"
                );

                extent.setSystemInfo(
                        "Operating System",
                        System.getProperty("os.name")
                );

                extent.setSystemInfo(
                        "Report Generated At",
                        LocalDateTime.now().format(
                                DateTimeFormatter.ofPattern(
                                        "dd-MMM-yyyy HH:mm:ss"
                                )
                        )
                );

            } catch (java.io.IOException e) {
                throw new RuntimeException(
                        "Failed to create the ExtentReports directory.", e
                );
            }
        }

        return extent;
    }
}
