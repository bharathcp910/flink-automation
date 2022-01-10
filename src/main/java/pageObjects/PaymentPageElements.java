package main.java.pageObjects;

public interface PaymentPageElements {

    String frmPayment = "stripe_checkout_app";
    String txtEmail = "email";
    String txtCardNumber = "card_number";
    String txtCardExpiration = "cc-exp";
    String txtCVC = "cc-csc";
    String btnPay = "submitButton";
    String lblPayAmount = ".iconTick";
}
