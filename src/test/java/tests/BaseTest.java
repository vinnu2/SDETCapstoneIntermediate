package tests;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.edge.EdgeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest {
	protected static WebDriver driver;
	protected WebDriverWait wait;
	private String browser;

	private static Logger logger = LogManager.getLogger(BaseTest.class);

	@BeforeClass
	@Parameters("browser")

	// Method to initialize the driver
	public void setUp(String browserName) {
		this.browser = browserName; // Assign browser value for use in tests
		System.out.println("Running tests on browser: " + browserName);

		try {
			// Initialize the WebDriver based on the browser parameter
			if (browserName.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			} else {
				logger.error("Browser not supported: {}", browserName);
				throw new IllegalArgumentException("Browser not supported: " + browserName);
			}

			// Configure driver settings
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			logger.info("Browser setup completed successfully for browser: {}", browserName);
		} catch (Exception e) {
			logger.error("Failed to initialize WebDriver: {}", e.getMessage());
			throw e;
		}
	}

	@Test
	public void testStockInformation() {
		// Browser-specific print statements
		switch (browser.toLowerCase()) {
		case "chrome":
			System.out.println("This is Chrome-specific test logic.");
			break;
		case "edge":
			System.out.println("This is Edge-specific test logic.");
			break;
		case "firefox":
			System.out.println("This is Firefox-specific test logic.");
			break;
		default:
			System.out.println("Unknown browser: " + browser);
			break;
		}

		System.out.println("Performing stock information validation for " + browser + " browser.");
	}

	// Close the WebDriver after test execution
	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		logger.info("Browser closed and resources cleaned up for browser: {}", browser);
	}

	@AfterMethod
	public void captureScreenshotOnFailure(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				logger.info("Capturing screenshot for faied test: {}", result.getName());
				TakesScreenshot ts = (TakesScreenshot) driver;
				File src = ts.getScreenshotAs(OutputType.FILE);
				String dateFormat = new SimpleDateFormat("YYYY_MM_DD_hh_mm_ss").format(new Date());

				String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + "_"
						+ dateFormat + ".png";
				File screenshotDir = new File(System.getProperty("user.dir") + "Screenshots");
				if (!screenshotDir.exists()) {
					screenshotDir.mkdir();
				}

				FileUtils.copyFile(src, new File(screenshotPath));
				logger.info("Screenshot saved at: {}", screenshotPath);
			} catch (IOException e) {
				logger.error("Failed to capture screenshot: {}", e.getMessage());
			}
		}
	}
}

