package test.java;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import main.java.utils.Constants;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {

    public ExtentHtmlReporter htmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest logger;

    @Parameters ("browser")
    @BeforeTest
    public void beforeTestMethod(String browser) {
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + File.separator + "reports" + File.separator
                + "AutomationReport" + browser + ".html");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation report");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.DARK);
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        extentReports.setSystemInfo("Author", "Bharath");
    }

    @Parameters ("browser")
    @BeforeMethod
    public void beforeMethodMethod(String browser, Method testMethod) {
        logger = extentReports.createTest(testMethod.getName());
        DriverFactory.setDriver(browser);
        DriverFactory.getDriver().manage().window().maximize();
        DriverFactory.getDriver().get(Constants.URL);
        DriverFactory.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void afterMethodMethod(ITestResult result) {
        if (result.isSuccess()) {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: " + methodName + " passed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
            logger.log(Status.PASS, markup);
        } else {
            String methodName = result.getMethod().getMethodName();
            String logText = "Test case: " + methodName + "failed";
            Markup markup = MarkupHelper.createLabel(logText, ExtentColor.RED);
            logger.log(Status.FAIL, markup);
        }
        DriverFactory.getDriver().quit();
    }

    @AfterTest
    public void afterTestMethod() {
        extentReports.flush();
    }
}
