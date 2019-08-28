package com.sample;

import javafx.beans.property.SimpleStringProperty;

public class Product {
    private SimpleStringProperty name;
    private float price;
    private int quantity;

    public Product(String name, float price, int quantity) {
        this.name = new SimpleStringProperty(name);
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name.get(); }

    public void setName(SimpleStringProperty name) { this.name = name; }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nPrice: " + price + "\nQuantity: " + quantity + "\n";
    }
}