package main.java.pageEvents;

import main.java.pageObjects.PaymentPageElements;
import main.java.utils.CommonFunctions;
import main.java.utils.Constants;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import test.java.BaseTest;
import test.java.DriverFactory;

import java.text.NumberFormat;

public class PaymentPageEvents {

    /**
     * Validates the input fields of the payment page by checking for valida and invalid inputs.
     */
    public void validatePageElements(int totalPrice) {
        CommonFunctions commonFunctions = new CommonFunctions();
        BaseTest.logger.info("Validate the payment window");
        // Switch to the payment iframe for validation
        DriverFactory.getDriver().switchTo().frame(PaymentPageElements.frmPayment);
        // Validations for email
        BaseTest.logger.info("Email validations");
            // Invalid email 1
        try {
            if (commonFunctions.getElement("id", PaymentPageElements.txtEmail).isEnabled())
                BaseTest.logger.pass("The email field is displayed and enabled");
            else {
                BaseTest.logger.fail("The email field is not displayed or enabled");
            }
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The email field is not present");
            Assert.fail();
        }
        commonFunctions.getElement("id", PaymentPageElements.txtEmail).sendKeys(Constants.invalidEmail1);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtEmail)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid email 1 is not accepted");
        else {
            BaseTest.logger.fail("Invalid email 1 is accepted");
        }
            // Invalid email 2
        commonFunctions.getElement("id", PaymentPageElements.txtEmail).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtEmail).sendKeys(Constants.invalidEmail2);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtEmail)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid email 2 is not accepted");
        else {
            BaseTest.logger.fail("Invalid email 2 is accepted");
        }
            // Valid email
        commonFunctions.getElement("id", PaymentPageElements.txtEmail).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtEmail).sendKeys(Constants.validEmail);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (!commonFunctions.getElement("id", PaymentPageElements.txtEmail)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Valid email is accepted");
        else {
            BaseTest.logger.fail("Valid email is not accepted");
            Assert.fail();
        }

        // Validations for card number
        BaseTest.logger.info("Card number validations");
            // Invalid card number 1
        try {
            if (commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).isEnabled())
                BaseTest.logger.pass("The card number field is displayed and enabled");
            else {
                BaseTest.logger.fail("The card number field is not displayed or enabled");
            }
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The card number field is not present");
            Assert.fail();
        }
        commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).sendKeys(Constants.invalidCardNumber1);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtCardNumber)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid card number 1 is not accepted");
        else {
            BaseTest.logger.fail("Invalid card number 1 is accepted");
        }
            // Invalid card number 2
        commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).sendKeys(Constants.invalidCardNumber2);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtCardNumber)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid card number 2 is not accepted");
        else {
            BaseTest.logger.fail("Invalid card number 2 is accepted");
        }
            // Invalid card number 3
        commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtCardNumber).sendKeys(Constants.invalidCardNumber3);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtCardNumber)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid card number 3 is not accepted");
        else {
            BaseTest.logger.fail("Invalid card number 3 is accepted");
        }

        // Validations for expiration date
        BaseTest.logger.info("Expiry date validations");
            // Invalid expiration date
        try {
            if (commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).isEnabled())
                BaseTest.logger.pass("The expiration date field is displayed and enabled");
            else {
                BaseTest.logger.fail("The expiration date field is not displayed or enabled");
            }
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The expiration date field is not present");
            Assert.fail();
        }
        commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).sendKeys(Constants.expiryMonth);
        commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).sendKeys("12");
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid expiration date is not accepted");
        else {
            BaseTest.logger.fail("Invalid expiration date is accepted");
        }
            // Valid expiration date
        commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).sendKeys(Constants.expiryMonth);
        commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration).sendKeys(Constants.expiryYear);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (!commonFunctions.getElement("id", PaymentPageElements.txtCardExpiration)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Valid expiration date is accepted");
        else {
            BaseTest.logger.fail("Valid expiration date is not accepted");
            Assert.fail();
        }

        // Validations for CVC
        BaseTest.logger.info("CVC validations");
            // Invalid CVC
        try {
            if (commonFunctions.getElement("id", PaymentPageElements.txtCVC).isEnabled())
                BaseTest.logger.pass("The CVC field is displayed and enabled");
            else {
                BaseTest.logger.fail("The CVC field is not displayed or enabled");
            }
        } catch (NoSuchElementException e) {
            BaseTest.logger.fail("The CVC field is not present");
            Assert.fail();
        }
        commonFunctions.getElement("id", PaymentPageElements.txtCVC).sendKeys(Constants.invalidCVC);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (commonFunctions.getElement("id", PaymentPageElements.txtCVC)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Invalid CVC is not accepted");
        else {
            BaseTest.logger.fail("Invalid CVC is accepted");
        }
            // Valid CVC
        commonFunctions.getElement("id", PaymentPageElements.txtCVC).clear();
        commonFunctions.getElement("id", PaymentPageElements.txtCVC).sendKeys(Constants.validCVC);
        commonFunctions.getElement("id", PaymentPageElements.btnPay).click();
        if (!commonFunctions.getElement("id", PaymentPageElements.txtCVC)
                .getAttribute("class").contains("invalid"))
            BaseTest.logger.pass("Valid CVC is accepted");
        else {
            BaseTest.logger.fail("Valid CVC is not accepted");
            Assert.fail();
        }

        // Validate Payment price
        BaseTest.logger.info("Validate if the payment price is equal to the total price");
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(true);
        if (commonFunctions.getElement("css", PaymentPageElements.lblPayAmount).getText().contains(
                numberFormat.format((double) totalPrice)))
            BaseTest.logger.pass("The payment price is equal to the total price");
        else {
            BaseTest.logger.fail("The payment price shown is incorrect and does not equal the total price");
            Assert.fail();
        }
    }
}
