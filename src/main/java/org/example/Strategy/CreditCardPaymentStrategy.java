package org.example.Strategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using credit card");
    }
}
