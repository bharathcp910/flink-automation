package main.java.pageObjects;

public interface CheckoutPageElements {

    String lblCheckoutHeading = "//h2[contains(text(), 'Checkout')]";
    String trTableRows = ".table tbody tr";
    String lblTotalPrice = "total";
    String btnPayWithCard = "//span[contains(text(), 'Pay with Card')]/ancestor::button[@type='submit']";
}
