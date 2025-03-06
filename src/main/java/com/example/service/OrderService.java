package com.example.service;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {
    OrderRepository orderRepository;

    public OrderService() {
        this.orderRepository = new OrderRepository();
    }

    public void addOrder(Order order) {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID());
        }

        orderRepository.addOrder(order);
    }

    public ArrayList<Order> getOrders() {
        return orderRepository.getOrders();
    }

    public Order getOrderById(UUID orderId) {
        return orderRepository.getOrderById(orderId);
    }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException {
        try {
            orderRepository.deleteOrderById(orderId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can not delete order with ID: " + orderId.toString(), e);
        }
    }
}