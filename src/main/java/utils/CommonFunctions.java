package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;
import test.java.DriverFactory;

import java.util.List;

public class CommonFunctions {

    public WebElement getElement(String identifier, String identifierValue) {
        switch (identifier.toLowerCase()) {
            case "id":
                return DriverFactory.getDriver().findElement(By.id(identifierValue));
            case "css":
                return DriverFactory.getDriver().findElement(By.cssSelector(identifierValue));
            case "xpath":
                return DriverFactory.getDriver().findElement(By.xpath(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getElements(String identifier, String identifierValue) {
        switch (identifier.toLowerCase()) {
            case "id":
                return DriverFactory.getDriver().findElements(By.id(identifierValue));
            case "css":
                return DriverFactory.getDriver().findElements(By.cssSelector(identifierValue));
            case "xpath":
                return DriverFactory.getDriver().findElements(By.xpath(identifierValue));
            default:
                return null;
        }
    }
}
