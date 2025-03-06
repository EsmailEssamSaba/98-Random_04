package com.example.model;


import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Component
public class User {
    private UUID id;
    private String name;
    private List<Order> orders=new ArrayList<>();
    public User() {
    }

    public User(String name, List<Order> orders) {
        this.name = name;
        this.orders = orders;

    }

    public UUID getId() {return id;}

    public void setId(UUID id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public List<Order> getOrders() {return orders;}

    public void setOrders(List<Order> orders) {this.orders = orders;}
    public User(String name) {this.name = name;}
}