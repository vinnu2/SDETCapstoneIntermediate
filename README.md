# SDETCapstoneIntermediate
#Java Case Study: Automated Testing to Verify Stock Information Display
1. Objective
This case study guides learners in building an automated test using Java and Selenium to verify the profit or loss of a specific stock on the NSE India website. The goal is to provide practical experience in web testing and result verification through automation.

2. Description
The automation script ensures that stock information, including the current market value, is displayed correctly on the NSE India website after searching for the stock "RCOM".

3. Prerequisites
Ensure the following tools and dependencies are installed and configured:
-Integrated Development Environment (IDE): IntelliJ IDEA or Eclipse
-Java Development Kit (JDK): Ensure JDK is installed and environment variables are set
-Selenium WebDriver Dependencies: Add Selenium to the project
-Browser Drivers: Install and configure necessary drivers (e.g., ChromeDriver)
-Testing Framework: Set up TestNG or JUnit
-Build Tool: Install and configure Maven or Gradle
-WebDriver Manager: For efficient browser driver management
-Logging Framework: Integrate Log4j or SLF4J for logs
-Reporting Tool: Implement ExtentReports or Allure
-Version Control: Use Git for tracking changes and push the code to a public repository
-Web Browser: Install a web browser (Chrome, Firefox, Edge)
-Mobile App Testing (Optional): If applicable, configure Appium for mobile testing

4. Tool Chain
-JDK – Required for writing and running Java code
-Selenium WebDriver – Automates browser interactions
-TestNG/JUnit – Testing frameworks for structuring tests
-Maven/Gradle – Build tools to manage dependencies
-WebDriver Manager – Handles browser driver setup
-Log4j/SLF4J – Captures logs during test execution
-ExtentReports/Allure – Generates test reports

5. Expected Deliverables
-Java Project: Organized project structure with necessary packages and classes
-Test Script: Java Selenium script to automate stock profit/loss verification
-Configuration Files: Maven or Gradle setup
-Test Data: Includes predefined purchase price and other parameters
-Documentation:README file with setup and execution instructions
-Test case documentation for "Verify Stock Information Display"
-Logging and Reporting:Logs capturing relevant details during test execution
-Integrated reporting tool to generate detailed test reports

6. Steps to Perform
-Setup Configure a Java project with Selenium WebDriver.
-Implement a test script that interacts with the NSE India website.
-Navigation to NSE India
-Launch the NSE India website using Selenium.
-Search for the stock "RCOM" dynamically.
-Dynamic Stock Search
-Modify the script to work with any NIFTY 50 stock (e.g., Tata Motors).
-Extend the script to retrieve and display 52-week high and low prices.
-Test Criteria
-Pass Criteria:Stock information is displayed correctly.The displayed values match those fetched by the automation script.
-Fail Criteria:Missing or incorrect stock details.Mismatch between displayed and actual values.
-Parallel Testing : Execute tests on multiple browsers (Chrome, Firefox, Edge).Configure TestNG/JUnit for concurrent test execution.Ensure consistency across browsers.
-Additional Notes : Take screenshots before and after retrieving stock information for documentation.
-Use logging and reporting for debugging and analysis.

7. Execution Instructions
-Clone the repository:git clone <repository-url>
-Open the project in Eclipse.
-Configure Maven.
-Run the test using TestNG.
-Check logs and reports for results.
