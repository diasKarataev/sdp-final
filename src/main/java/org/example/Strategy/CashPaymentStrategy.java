package org.example.Strategy;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Оплата " + amount + "тг. наличными");
    }
}