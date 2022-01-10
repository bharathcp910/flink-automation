package main.java.pageEvents;

import main.java.pageObjects.TemperaturePageElements;
import main.java.utils.CommonFunctions;
import org.testng.Assert;
import test.java.BaseTest;

import java.util.NoSuchElementException;

public class TemperaturePageEvents {

    /**
     * Validate the elements in the temperature page.
     */
    public void validateTemperaturePageElements() {
        CommonFunctions commonFunctions = new CommonFunctions();
        BaseTest.logger.info("Validate temperature page elements.");
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.lblPageHeading).isDisplayed());
                BaseTest.logger.pass("The heading of the temperature page is displayed correctly.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The heading of the temperature page is not present or incorrect.");
        }
        try {
            if (commonFunctions.getElement("id", TemperaturePageElements.lblTemperature).isDisplayed());
                BaseTest.logger.pass("The current temperature is shown.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The current temperature is not shown.");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.lblMoisturizerHeading).isDisplayed());
                BaseTest.logger.pass("The heading of the moisturizer section is displayed correctly.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The heading of the moisturizer section is not present or incorrect.");
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.lblSunscreenHeading).isDisplayed());
                BaseTest.logger.pass("The heading of the sunscreen section is displayed correctly.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The heading of the sunscreen section is not present or incorrect.");
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.btnBuyMoisturizers).isDisplayed());
                BaseTest.logger.pass("The button to buy moisturizers is displayed.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The button to buy moisturizers is not displayed.");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.btnBuyMoisturizers).isEnabled());
                BaseTest.logger.pass("The button to buy moisturizers is enabled.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The button to buy moisturizers is not enabled.");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.btnBuySunscreens).isDisplayed())
                BaseTest.logger.pass("The button to buy sunscreens is displayed.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The button to buy sunscreens is not displayed.");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", TemperaturePageElements.btnBuySunscreens).isEnabled())
                BaseTest.logger.pass("The button to buy sunscreens is enabled.");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The button to buy sunscreens is not enabled.");
            Assert.fail();
        }
    }

    /**
     * Select an item based on the temperature.
     * @return the type of item that was selected.
     */
    public String selectItemType() {
        BaseTest.logger.info("Select the item type based on the temperature.");
        CommonFunctions commonFunctions = new CommonFunctions();
        String temperatureReading = commonFunctions.getElement("id", TemperaturePageElements.lblTemperature).getText();
        int temperature = Integer.parseInt(temperatureReading.split(" ")[0]);
        if (temperature < 19) {
            commonFunctions.getElement("xpath", TemperaturePageElements.btnBuyMoisturizers).click();
            return "moisturizers";
        }
        if (temperature > 34) {
            commonFunctions.getElement("xpath", TemperaturePageElements.btnBuySunscreens).click();
            return "sunscreens";
        }
        return null;
    }
}
