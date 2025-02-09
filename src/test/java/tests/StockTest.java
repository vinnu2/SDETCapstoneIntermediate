package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import config.ConfigReader;
import pages.HomePage;
import pages.SearchResultpage;

public class StockTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(StockTest.class);

	@Test
	public void verifyStockInformation() throws InterruptedException {

		logger.info("Starting the stock information verification test");

		// Get Base URL and stock Name from config.properties
		String baseUrl = ConfigReader.getProperty("baseUrl");
		String stockName = ConfigReader.getProperty("stockName");

		String url = baseUrl + "?symbol=" + stockName;

		logger.info("Base URL : {}", url);
		logger.info("Stock Name :{}", stockName);

		// Navigate to NSE Website
		driver.get(url);
		logger.info("Navigated to NSE India website");

		// Create page object for home page
		HomePage homepage = new HomePage(driver);

		// Search for stock
		// homepage.searchStock(stockName);

		// create page object for SearchResults
		SearchResultpage searchResultpage = new SearchResultpage(driver);

		// Expected values
		System.out.println("**************Expected values**********");
		String expectedCurrentValue = ConfigReader.getProperty("expectedCurrentValue");
		String expectedHighValue = ConfigReader.getProperty("expectedHighValue");
		String expectedLowValue = ConfigReader.getProperty("expectedLowValue");

		System.out.println("CurrentValue :" + expectedCurrentValue);
		System.out.println("HighValue :" + expectedHighValue);
		System.out.println("LowValue :" + expectedLowValue);

		// Actual values
		System.out.println("************Actual values*********");
		String actualCurrentValue = searchResultpage.getCurrentValue();
		String actualHighValue = searchResultpage.getHighValue();
		String actualLowValue = searchResultpage.getLowValue();

		System.out.println("CurrentValue :" + actualCurrentValue);
		System.out.println("HighValue :" + actualHighValue);
		System.out.println("LowValue :" + actualLowValue);

		// assertions
		Assert.assertEquals(actualCurrentValue, expectedCurrentValue, "Current value does not match");
		Assert.assertEquals(actualHighValue, expectedHighValue, "High value does not match");
		Assert.assertEquals(actualLowValue, expectedLowValue, "Low value does not match");

		// Calculate profit/Loss
		searchResultpage.calculateProfitLoss();

		logger.info("stock information verification test completed successfully");
	}

}
