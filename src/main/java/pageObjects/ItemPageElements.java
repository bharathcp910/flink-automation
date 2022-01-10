package main.java.pageObjects;

public interface ItemPageElements {

    String lblSunscreensHeading = "//h2[contains(text(), 'Sunscreens')]";
    String lblMoisturizersHeading = "//h2[contains(text(), 'Moisturizers')]";
    String lblItemsInCart = "cart";
    String btnAdd = "//button[contains(text(), 'Add')]";
    String btnCheckout = "//button[contains(text(), 'Cart -') and @onclick='goToCart()']";
}
