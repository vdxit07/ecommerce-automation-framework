# E-Commerce Automation Framework

A UI test automation framework built with **Java, Selenium WebDriver, TestNG, and Maven** to automate end-to-end testing of the SauceDemo e-commerce application.

## Tech Stack

- Java 21
- Selenium WebDriver 4.41.0
- TestNG 7.10
- Maven
- ExtentReports 5.1.2
- Git and GitHub
- GitHub Actions for CI

## Application Under Test

[SauceDemo](https://www.saucedemo.com/)

## Features

- Page Object Model (POM) structure
- Automated login testing
- Positive and negative login scenarios
- Add-to-cart validation
- End-to-end checkout testing
- Explicit waits for synchronization
- Assertions to validate application behavior
- HTML test reporting with ExtentReports
- Automated test execution through GitHub Actions

## Project Structure

```text
ecommerce-automation-framework/
├── .github/
│   └── workflows/
│       └── maven.yml
├── src/
│   ├── main/
│   │   └── java/com/vedansh/qa/
│   │       ├── LoginPage.java
│   │       ├── ProductsPage.java
│   │       ├── CartPage.java
│   │       ├── CheckoutPage.java
│   │       └── WaitUtils.java
│   └── test/
│       └── java/com/vedansh/qa/
│           ├── BaseTest.java
│           ├── LoginTest.java
│           ├── CartTest.java
│           └── CheckOutTest.java
├── pom.xml
└── README.md
```

## Prerequisites

Install the following:

- Java JDK 21
- Apache Maven
- Git
- Google Chrome

Verify Java and Maven:

```bash
java -version
mvn -version
```

## How to Run Tests

Clone the repository:

```bash
git clone https://github.com/vdxit07/ecommerce-automation-framework.git
```

Navigate to the project directory:

```bash
cd ecommerce-automation-framework
```

Run the test suite:

```bash
mvn clean test
```

## Test Coverage

| Test Area | Validation |
|---|---|
| Login | Valid and invalid login scenarios |
| Shopping Cart | Add a product and verify cart contents |
| Checkout | Complete checkout and verify the order confirmation |

## Test Reporting

The framework uses ExtentReports to generate an HTML test report.

After execution, check the project's `target` directory for the generated report. The exact report filename depends on the reporting configuration.

## Continuous Integration

GitHub Actions runs the Maven test workflow defined in:

`.github/workflows/maven.yml`

You can view workflow runs in the repository's **Actions** tab:

https://github.com/vdxit07/ecommerce-automation-framework/actions

## Repository

https://github.com/vdxit07/ecommerce-automation-framework

## Author

Vedansh Dixit
