package test.java;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

public class DriverFactory {
    private static ThreadLocal<WebDriver> driver;

    public synchronized static void setDriver (String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "drivers"
                    + File.separator + "geckodriver.exe");
            driver = ThreadLocal.withInitial(() -> new FirefoxDriver());
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "drivers"
                    + File.separator + "chromedriver");
            driver = ThreadLocal.withInitial(() -> new ChromeDriver());
        }
    }
    public synchronized static WebDriver getDriver () {
        return driver.get();
    }
}
