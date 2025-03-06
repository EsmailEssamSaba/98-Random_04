package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {

    public OrderRepository() {
    }

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/orders.json";
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

    public void addOrder(Order order) {
        super.save(order);
    }

    public ArrayList<Order> getOrders() {
        return super.findAll();
    }

    public Order getOrderById(UUID orderId) {
        ArrayList<Order> orders = super.findAll();

        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }

        return null;
    }

    public void deleteOrderById(UUID orderId) {
        ArrayList<Order> orders = super.findAll();

        orders.removeIf(order -> order.getId().equals(orderId));

        super.overrideData(orders);
    }
}