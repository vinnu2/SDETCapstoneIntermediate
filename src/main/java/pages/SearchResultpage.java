package pages;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultpage {

	private WebDriver driver;

	private static final Logger logger = LogManager.getLogger(SearchResultpage.class);

	private By currentValue = By.cssSelector("#quoteLtp");
	private By highValueText = By.xpath("//div[@class='col-md-3 card-spacing priceinfodiv']//tbody/tr[1]/td[1]");
	private By lowValueText = By.xpath("//div[@class='col-md-3 card-spacing priceinfodiv']//tbody/tr[2]/td[1]");

	public SearchResultpage(WebDriver driver) {
		this.driver = driver;
	}

	public String getCurrentValue() {
		logger.info("Waiting for the current value element to become visible");

		// wait for the current value element
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement currentValueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(currentValue));

		// Retrieve the text
		String currentValueText = currentValueElement.getText();

		logger.info("current stock value retrieved");

		return currentValueText;
	}

	public String getHighValue() {
		logger.info("Waiting for the 52-week high value element to become visible");

		// wait for the high value row
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement highValueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(highValueText));

		// Retrieve sibling vale using relative xpath
		String highValueTextContent = highValueElement.getText();
		String fiftyTwoWeekHighValue = highValueElement.findElement(By.xpath("//span[@id='week52highVal']")).getText();

		logger.info("52 week high value retrieved");

		return fiftyTwoWeekHighValue;
	}

	public String getLowValue() {
		logger.info("Waiting for the 52-week low value element to become visible");

		// wait for the low value row
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement lowValueElement = wait.until(ExpectedConditions.visibilityOfElementLocated(lowValueText));

		// Retrieve sibling vale using relative xpath
		String lowValueTextContent = lowValueElement.getText();
		String fiftyTwoWeekLowValue = lowValueElement.findElement(By.xpath("//span[@id='week52lowVal']")).getText();

		logger.info("52 week low value retrieved");

		return fiftyTwoWeekLowValue;
	}

	// Calculate Profit/Loss
	public void calculateProfitLoss() {

		// Get values as strings
		System.out.println("************calculate profit and loss***************");
		String currentValueStr = getCurrentValue();
		String highValueStr = getHighValue();
		String lowValueStr = getLowValue();
		System.out.println("CurrentValue :" + currentValueStr);
		System.out.println("HighValue :" + highValueStr);
		System.out.println("LowValue :" + lowValueStr);

		logger.info("Calculating values for profit and loss");

		if (currentValueStr.isEmpty() || highValueStr.isEmpty() || lowValueStr.isEmpty()) {
			System.out.println("Error: Input values cannot be empty.");
		} else {
			try {
				// Remove commas and convert to double
				double current = Double.parseDouble(currentValueStr.replace(",", ""));
				double high = Double.parseDouble(highValueStr.replace(",", ""));
				double low = Double.parseDouble(lowValueStr.replace(",", ""));

				// Calculate profit or loss
				double profitFromLow = ((current - low) / low) * 100;
				double lossFromHigh = ((current - high) / high) * 100;

				// Print the results
				System.out.printf("Profit from low: %.2f%%\n", profitFromLow);
				System.out.printf("Loss from high: %.2f%%\n", lossFromHigh);

			} catch (NumberFormatException e) {
				System.out.println("Error: Invalid input. Please enter numeric values.");
			}
		}
	}

}
