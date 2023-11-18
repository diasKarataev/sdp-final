package org.example.Strategy;

public class PayPalPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }
}