package listeners;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.ITestListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting implements ITestListener {

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext Context) {

		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports+myreports.html");

		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("QA Name", "Vineetha");
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser name", "Chrome");
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is :" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is :" + result.getName());
		test.log(Status.FAIL, "Test case FAILED cause is :" + result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Test case SKIPPED is :" + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

//    private void attachScreenshot(String testName) {
//        if (driver != null) {
//            TakesScreenshot ts = (TakesScreenshot) driver;
//            File source = ts.getScreenshotAs(OutputType.FILE);
//            String dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
//            String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + testName + "_" + dateFormat + ".png";
//
//            try {
//                FileUtils.copyFile(source, new File(screenshotPath));
//                test.get().addScreenCaptureFromPath(screenshotPath);
//            } catch (IOException e) {
//                test.get().log(Status.INFO, "Unable to attach screenshot: " + e.getMessage());
//            }
//        } else {
//            test.get().log(Status.INFO, "WebDriver instance is null. Screenshot not captured.");
//        }
//    }
}
