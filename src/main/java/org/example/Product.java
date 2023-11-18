package org.example;

public class Product {
    private static int id_generator;
    private int id;
    private String title;
    private String description;
    private int price;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public static int getId_generator() {
        return id_generator;
    }

    public static void setId_generator(int id_generator) {
        Product.id_generator = id_generator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String title, String description, int price) {
        id_generator += 1;
        id  = id_generator;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}