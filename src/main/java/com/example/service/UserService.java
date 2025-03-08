package com.example.service;

import com.example.model.Product;
import com.example.model.User;
import com.example.model.Cart;
import com.example.model.Order;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {
    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderService orderService;

    public UserService(UserRepository userRepository, CartService cartService, OrderService orderService) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository cannot be null");
        this.cartService = Objects.requireNonNull(cartService, "CartService cannot be null");
        this.orderService = Objects.requireNonNull(orderService, "OrderService cannot be null");
    }

    public User addUser(User user) {
        Objects.requireNonNull(user, "User cannot be null");
        userRepository.addUser(user);
        return user;
    }

    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    public User getUserById(UUID id) {
        Objects.requireNonNull(id, "User ID cannot be null");
        return userRepository.getUserById(id);
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        return userRepository.getOrdersByUserId(userId);
    }

    public void emptyCart(UUID userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        cartService.emptyCart(userId);
    }

    public void addOrderToUser(UUID userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");

        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null || cart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty or not found for user: " + userId);
        }

        double total = cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
        Order order = new Order(UUID.randomUUID(), userId, total, cart.getProducts());

        orderService.addOrder(order);
        userRepository.addOrderToUser(userId, order);
        emptyCart(userId);
        cartService.save(cart);
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        Objects.requireNonNull(orderId, "Order ID cannot be null");
        userRepository.removeOrderFromUser(userId, orderId);
    }

    public void deleteUserById(UUID userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        userRepository.deleteUserById(userId);
    }
}
