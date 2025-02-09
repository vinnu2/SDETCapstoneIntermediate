package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	private WebDriver driver;

	private static final Logger logger = LogManager.getLogger(HomePage.class);

	private By searchBox = By.id("header-search-input");
	private By stockSymbol = By.xpath("//ul[@id='header-search-input_listbox']/li[1]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	/*
	 * public void searchStock(String stockName){
	 * logger.info("Searching for stock :stockName");
	 * 
	 * driver.findElement(searchBox).sendKeys(stockName);
	 * 
	 * //wait for the auto-suggestions to appear WebDriverWait wait = new
	 * WebDriverWait(driver,Duration.ofSeconds(10)); WebElement autoSuggestion =
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(stockSymbol));
	 * 
	 * //Click on the suggestion autoSuggestion.click();
	 * 
	 * }
	 */
}
