# E-Commerce Automation Framework

A Java-based test automation framework demonstrating UI automation, REST API testing, SQL database validation, test reporting, and CI/CD using GitHub Actions.

## Tech Stack

- **Language:** Java
- **Build Tool:** Maven
- **UI Automation:** Selenium WebDriver
- **Test Framework:** TestNG
- **API Testing:** REST Assured
- **Database:** MySQL, JDBC
- **Reporting:** ExtentReports and TestNG/Surefire reports
- **CI/CD:** GitHub Actions
- **Version Control:** Git and GitHub

## Project Coverage

### 1. UI Automation
- Valid and invalid login scenarios
- Empty username and password validation
- Locked-out user validation
- Shopping cart add/remove functionality
- Checkout workflow

### 2. REST API Testing
- Retrieve a post using `GET /posts/1`
- Validate response status and JSON fields
- Verify a nonexistent post returns HTTP `404`
- Configure connection and response timeouts

### 3. Database Testing
- Connect to MySQL using JDBC
- Validate product pricing in the database
- Work with product and order tables
- Configure database credentials through the `ECOMMERCE_DB_PASSWORD` environment variable

### 4. Reporting
- TestNG/Surefire test results
- ExtentReports HTML report
- Screenshot capture for failed UI tests

### 5. CI/CD
- GitHub Actions workflow triggered on pushes and pull requests to `main`
- Java 17 environment
- MySQL service for database tests
- Automated Maven test execution
- Report artifacts uploaded for download

## Prerequisites

- JDK 17 or compatible newer JDK
