package test.java;

import main.java.pageEvents.CheckoutPageEvents;
import main.java.pageEvents.ItemPageEvents;
import main.java.pageEvents.PaymentPageEvents;
import main.java.pageEvents.TemperaturePageEvents;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class E2eFlowTest extends BaseTest {

    TemperaturePageEvents temperaturePageEvents;
    ItemPageEvents itemPageEvents;
    CheckoutPageEvents checkoutPageEvents;
    PaymentPageEvents paymentPageEvents;

    @BeforeClass
    public void init() {
        temperaturePageEvents = new TemperaturePageEvents();
        itemPageEvents = new ItemPageEvents();
        checkoutPageEvents = new CheckoutPageEvents();
        paymentPageEvents = new PaymentPageEvents();
    }

    /**
     * An end to end test which tests an entire flow of the application.
     */
    @Test
    public void e2eTest() {

        // Events of the temperature page.
        temperaturePageEvents.validateTemperaturePageElements();
        String itemType = temperaturePageEvents.selectItemType();

        // Events of the item page.
        itemPageEvents.validatePageItems(itemType);
        List<Map<String, String>> items = itemPageEvents.addItems();
        itemPageEvents.checkout();

        // Events of the checkout page.
        checkoutPageEvents.validateElements();
        checkoutPageEvents.validateItems(items);
        int totalPrice = checkoutPageEvents.validateTotalPrice(items);
        checkoutPageEvents.pay();

        // Events of the payment page.
        paymentPageEvents.validatePageElements(totalPrice);
    }
}
