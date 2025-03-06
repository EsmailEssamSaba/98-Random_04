package com.example.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

@Component
public class Order {
    private UUID id;
    private UUID userId;
    private double totalPrice;
    private List<Product> products=new ArrayList<>();


    public Order(){

    }
    public Order(UUID id, UUID userId, double totalPrice, List<Product> products){
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.products = products;
    }
    public Order(UUID userId, double totalPrice, List<Product> products){
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.products = products;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}