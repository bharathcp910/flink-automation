package main.java.pageEvents;

import main.java.pageObjects.ItemPageElements;
import main.java.utils.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import test.java.BaseTest;

import java.util.*;

public class ItemPageEvents {

    /** Validates the heading of the page, and empty cart.
     * @param itemType is the type of item based on which the heading will be validated.
     */
    public void validatePageItems(String itemType) {
        CommonFunctions commonFunctions = new CommonFunctions();
        // Validate heading based on the type of item.
        if (itemType.equals("sunscreens")) {
            try {
                if (commonFunctions.getElement("xpath", ItemPageElements.lblSunscreensHeading).isDisplayed())
                    BaseTest.logger.pass("Sunscreens has been selected");
            } catch (NoSuchElementException e) {
                BaseTest.logger.fail("Sunscreens has not been selected or is not displayed");
            }
        } else if (itemType.equals("moisturizers")) {
            try {
                if (commonFunctions.getElement("xpath", ItemPageElements.lblMoisturizersHeading).isDisplayed())
                    BaseTest.logger.pass("Moisturizers has been selected");
            } catch (NoSuchElementException e) {
                BaseTest.logger.fail("Moisturizers has not been selected or is not displayed");
            }
        }
        // Validate that the cart is initially empty.
        BaseTest.logger.info("Validate if the cart is empty");
        try {
            if (commonFunctions.getElement("id", ItemPageElements.lblItemsInCart).getText().
                    equalsIgnoreCase("empty"))
                BaseTest.logger.pass("The cart is empty");
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The cart is not present or not empty");
            Assert.fail();
        }
    }

    /**
     * Adds a random number of items to the cart.
     * @return a list containing the items added to the cart.
     */
    public List<Map<String, String>> addItems() {
        BaseTest.logger.info("Adding random items to the cart and validating");
        // Declarations
        CommonFunctions commonFunctions = new CommonFunctions();
        List<Map<String, String>> items = new ArrayList<>();
        Map<String, String> itemDetails;
        // Validate that items are available on the page.
        List<WebElement> itemCards = commonFunctions.getElements("xpath", ItemPageElements.btnAdd);
        if (itemCards.size() > 0) {
            BaseTest.logger.pass("Items are available for selection");
        } else {
            BaseTest.logger.fail("No items available for selection");
        }
        Assert.assertTrue(itemCards.size() > 0);
        // Pick a random number of items to add (1 to 5 items)
        int itemsToAdd = new Random().nextInt(itemCards.size()) + 1;
        // Loop to add elements until the expected number of elements are added.
        for (int i = 1; i <= itemsToAdd; i++) {
//            // Randomly generate the number of elements to be added.
//            int itemIndex = new Random().nextInt(itemCards.size()) + 1;
            // Create a new hashmap to add the itemDetails
            itemDetails = new HashMap<>();
            // Get xpath for the specific add button and add the element to the cart.
            String addButtonXpath = "(//button[contains(text(), 'Add')])[" + i + "]";
            commonFunctions.getElement("xpath", addButtonXpath).click();
            // Get the xpath for name and add it to the map.
            String nameXpath = "(" + addButtonXpath + "/preceding-sibling::p)[1]";
            itemDetails.put("name", commonFunctions.getElement("xpath", nameXpath).getText().trim());
            // Extract the price value as a string from the price text and add it to the map.
            String priceXpath = "(" + addButtonXpath + "/preceding-sibling::p)[2]";
            String[] itemPriceValue = commonFunctions.getElement("xpath", priceXpath).getText().split(" ");
            String itemPrice = itemPriceValue[itemPriceValue.length - 1].trim();
            itemDetails.put("price", itemPrice);
            // Add the map to the items array.
            items.add(itemDetails);
        }
        // Validate that the expected number of items have been added to the cart
        if (commonFunctions.getElement("id", ItemPageElements.lblItemsInCart).getText().
                equalsIgnoreCase(itemsToAdd + " item(s)"))
            BaseTest.logger.pass("The expected number of items have been added in the cart.");
        else {
            BaseTest.logger.fail("The number of items added and the number of items in the cart do not match");
            Assert.fail();
        }

        // Return the items that are added to the cart
        return items;
    }

    /**
     * Navigates to the checkout page by clicking on the cart.
     */
    public void checkout() {
        CommonFunctions commonFunctions = new CommonFunctions();
        // Click on the cart to checkout.
        commonFunctions.getElement("xpath", ItemPageElements.btnCheckout).click();
    }
}
