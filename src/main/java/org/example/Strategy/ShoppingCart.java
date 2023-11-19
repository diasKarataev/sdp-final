package org.example.Strategy;

import org.example.Factory.Membership;
import org.example.Factory.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShoppingCart {
    private int amount;
    private PaymentStrategy paymentStrategy;
    private List<Membership> productList = new ArrayList<>();

    public void updateProduct(Membership product){
        Optional<Membership> existingProduct = productList.stream()
                .filter(p -> p.getClass().equals(product.getClass()))
                .findFirst();

        if (existingProduct.isPresent()) {
            // Если абонемент уже существует, заменяем его в списке
            int index = productList.indexOf(existingProduct.get());
            productList.set(index, product);
        } else {
            // Если абонемент не существует, добавляем его в список
            productList.add(product);
        }
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }

    public void addToCart(Membership product){
        productList.add(product);
    }

    public void clearCart(){
        productList.clear();
    }
    public void remove(Membership product){
        productList.remove(product);
    }

    public List<Membership> getProductList() {
        return productList;
    }

    public int getAmount() {
        amount = 0;
        for(Membership membership : productList){
            amount += membership.getPrice();
        }
        return amount;
    }
}