package main.java.pageEvents;

import main.java.pageObjects.CheckoutPageElements;
import main.java.utils.CommonFunctions;
import org.testng.Assert;
import test.java.BaseTest;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CheckoutPageEvents {

    /**
     * Validates the elements of the checkout page.
     */
    public void validateElements() {
        BaseTest.logger.info("Validate Elements of the checkout page");
        CommonFunctions commonFunctions = new CommonFunctions();
        try {
            if (commonFunctions.getElement("xpath", CheckoutPageElements.lblCheckoutHeading).isDisplayed())
                BaseTest.logger.pass("The heading for the checkout page is displayed correctly");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The heading for the checkout page is not present or incorrect");
        }
        try {
            if (commonFunctions.getElement("id", CheckoutPageElements.lblTotalPrice).isDisplayed())
                BaseTest.logger.pass("The total price of the purchase is displayed");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The total price of the purchase is not shown");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", CheckoutPageElements.btnPayWithCard).isDisplayed())
                BaseTest.logger.pass("The pay with card button is displayed");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The pay with card button is not displayed");
            Assert.fail();
        }
        try {
            if (commonFunctions.getElement("xpath", CheckoutPageElements.btnPayWithCard).isEnabled())
                BaseTest.logger.pass("The pay with card button is enabled");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The pay with card button is not enabled");
            Assert.fail();
        }
    }

    /**
     * Validates if the elements that were selected in the previous page are shown correctly.
     * Validation is done by checking name and price
     * @param items are the list of items added in the ItemPage.
     */
    public void validateItems(List<Map<String, String>> items) {
        CommonFunctions commonFunctions = new CommonFunctions();
        // Validate that the number of items added are accurate.
        BaseTest.logger.info("Validate if the number of items shown are the same as added");
        if (items.size() ==
                commonFunctions.getElements("css", CheckoutPageElements.trTableRows).size())
            BaseTest.logger.pass("The number of items added are as expected");
        else {
            BaseTest.logger.fail("The number of items added are not as expected");
        }
        Assert.assertTrue(items.size() ==
                commonFunctions.getElements("css", CheckoutPageElements.trTableRows).size());

        // Loop through the items and validate the name and price of each item.
        BaseTest.logger.info("Validate each item added");
        items.forEach(item -> {
            // Check the name and price are shown correctly on the UI.
            String nameXpath = "//td[contains(text(), '" + item.get("name") + "')]";
            try {
                if (commonFunctions.getElement("xpath", nameXpath).isDisplayed())
                    BaseTest.logger.pass("The item " + item.get("name") + "is displayed");
            } catch (NoSuchElementException e) {
                BaseTest.logger.fail("The item " + item.get("name") + "is not displayed");
            }
            String priceXpath = "//td[contains(text(), '" + item.get("name") + "')]/following-sibling::td";
            try {
                if (commonFunctions.getElement("xpath", priceXpath).getText().equals(item.get("price")))
                    BaseTest.logger.pass("The price of the item is as expected");
            } catch (NoSuchElementException e) {
                BaseTest.logger.fail("The price of the item is incorrect");
            }
        });
    }

    /**
     * Validates if the total price is calculated and shown correctly.
     * @param items are the list of items added in the ItemPage.
     */
    public int validateTotalPrice(List<Map<String, String>> items) {
        CommonFunctions commonFunctions = new CommonFunctions();
        BaseTest.logger.info("Validate the total price");
        // Calculate total price.
        int totalPrice = 0;
        for (Map<String, String> item : items) {
            totalPrice += Integer.parseInt(item.get("price"));
        }
        // Validate if it is shown correctly on the UI.
        if (commonFunctions.getElement("id", CheckoutPageElements.lblTotalPrice).getText().
                contains(String.valueOf(totalPrice)))
            BaseTest.logger.pass("The total price of the purchase is as expected");
        else
            BaseTest.logger.pass("The total price of the purchase is not as expected");
        return totalPrice;
    }

    /**
     * Clicks on the pay with card button.
     */
    public void pay() {
        CommonFunctions commonFunctions = new CommonFunctions();
        // Click on pay with card.
        BaseTest.logger.info("Click on pay with card");
        commonFunctions.getElement("xpath", CheckoutPageElements.btnPayWithCard).click();
    }
}
