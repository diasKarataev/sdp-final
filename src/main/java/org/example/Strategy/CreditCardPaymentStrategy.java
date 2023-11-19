package org.example.Strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Оплата " + amount + " кредитной картой");
    }
}
