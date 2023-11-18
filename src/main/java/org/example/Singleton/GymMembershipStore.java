package org.example.Singleton;

import org.example.ERole;
import org.example.Order;
import org.example.Factory.Product;
import org.example.User;

import java.util.ArrayList;
import java.util.List;

public
class GymMembershipStore {
    private static GymMembershipStore instance;
    private List<Product> products;
    private List<Order> orders;

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Order> getOrdersByUser(User user){
        List<Order> userOrders = new ArrayList<>();
        for(Order order : orders){
            if(order.getOrderedUser() == user){
                userOrders.add(order);
            }
        }
        return userOrders;
    }

    private List<User> users;

    private GymMembershipStore() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
        users = new ArrayList<>();
    }

    public static GymMembershipStore getInstance() {
        if (instance == null) {
            instance = new GymMembershipStore();
        }
        return instance;
    }

    public void removeOrder(int orderId){
        orders.removeIf(order -> order.getId() == orderId);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public Product getProductById(int id){
        for(Product product : products){
            if(product.getId() == id){
                return product;
            }
        }
        return null;
    }

    public String getUserPasswordByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getPassword();
            }
        }
        return null;
    }
    public ERole getRolePasswordByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getRole();
            }
        }
        return null;
    }

    public String getUserNameByEmail(String email) {
        for(User user : users){
            if(user.getEmail().equals(email)){
                return user.getName();
            }
        }
        return null;
    }



    public Order getOrderById(int id){
        for(Order order : orders) {
            if (order.getId() == id) return order;
        }
            return null;
        }
    }
