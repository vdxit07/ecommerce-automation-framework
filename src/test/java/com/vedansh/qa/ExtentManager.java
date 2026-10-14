
package com.vedansh.qa;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static synchronized ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter("target/extent-report.html");

            sparkReporter.config().setDocumentTitle("E-commerce Automation Report");
            sparkReporter.config().setReportName("Selenium Test Execution");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);

            extent.setSystemInfo("Project", "E-commerce Automation Framework");
            extent.setSystemInfo("Automation Tool", "Selenium WebDriver");
            extent.setSystemInfo("Test Framework", "TestNG");
        }

        return extent;
    }
}
