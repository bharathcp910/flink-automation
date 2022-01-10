package main.java.pageObjects;

public interface TemperaturePageElements {

    String lblPageHeading = "//h2[contains(text(), 'Current temperature')]";
    String lblTemperature = "temperature";
    String lblMoisturizerHeading = "//h3[contains(text(), 'Moisturizers')]";
    String lblSunscreenHeading = "//h3[contains(text(), 'Sunscreens')]";
    String btnBuyMoisturizers = "//button[contains(text(), 'Buy moisturizers')]";
    String btnBuySunscreens = "//button[contains(text(), 'Buy sunscreens')]";

}
