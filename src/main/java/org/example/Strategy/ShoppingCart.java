package org.example.Strategy;

import org.example.Factory.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    private List<Product> productList = new ArrayList<>();


    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }

    public void addToCart(Product product){
        productList.add(product);
    }

    public void clearCart(){
        productList.clear();
    }
    public void remove(Product product){
        productList.remove(product);
    }

}