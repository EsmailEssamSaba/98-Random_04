package com.example.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class CartModel {
    private UUID id;
    private UUID userId;
    private List<ProductModel> products=new ArrayList<>();

    public CartModel(){

    }
    public CartModel(UUID id, UUID userId, List<ProductModel> products) {
        this.id = id;
        this.userId = userId;//needs to be cartId
        this.products = products;
    }

    public CartModel(UUID userId, List<ProductModel> products) {
        this.userId = userId; //needs to be cartId
        this.products = products;
    }

    public UUID getId() {
        return userId;
    }//needs to be cartId

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;//needs to be cartId
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }//needs to be cartId

    public List<ProductModel> getProducts() {
        return products;
    }

    public void setProducts(List<ProductModel> products) {
        this.products = products;
    }
}