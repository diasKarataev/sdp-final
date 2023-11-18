package org.example;

import org.example.Factory.Product;

public class Order {
    private static int id_generator = 0;
    private int id;
    private User orderedUser;
    private Product orderedProduct;
    private String address;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderedUser=" + orderedUser +
                ", orderedProduct=" + orderedProduct +
                ", address='" + address + '\'' +
                '}';
    }

    public Order(User orderedUser, Product orderedProduct, String address) {
        id_generator += 1;
        this.id += id_generator;
        this.orderedUser = orderedUser;
        this.orderedProduct = orderedProduct;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOrderedUser() {
        return orderedUser;
    }

    public void setOrderedUser(User orderedUser) {
        this.orderedUser = orderedUser;
    }

    public Product getOrderedProduct() {
        return orderedProduct;
    }

    public void setOrderedProduct(Product orderedProduct) {
        this.orderedProduct = orderedProduct;
    }
}
