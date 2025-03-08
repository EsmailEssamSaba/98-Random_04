package com.example.service;

import com.example.repository.CartRepository;
import org.springframework.stereotype.Service;
import com.example.model.Cart;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart> {
    CartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }

    public void save(Cart cart) {
        Objects.requireNonNull(cart, "Cart cannot be null");
        cartRepository.save(cart);
    }

    public Cart addCart(Cart cart) {
        Objects.requireNonNull(cart, "Cart cannot be null");
        return cartRepository.addCart(cart);
    }

    public Cart getCartById(UUID cartId) {
        Objects.requireNonNull(cartId, "Cart ID cannot be null");
        return cartRepository.getCartById(cartId);
    }

    public ArrayList<Cart> getCarts() {
        return cartRepository.getCarts();
    }

    public Cart getCartByUserId(UUID userId) {
        Objects.requireNonNull(userId, "User ID cannot be null");
        return cartRepository.getCartByUserId(userId);
    }

    public void addProductToCart(UUID cartId, Product product) {
        Objects.requireNonNull(cartId, "Cart ID cannot be null");
        Objects.requireNonNull(product, "Product cannot be null");
        cartRepository.addProductToCart(cartId, product);
    }

    public void deleteProductFromCart(UUID cartId, Product product) {
        Objects.requireNonNull(cartId, "Cart ID cannot be null");
        Objects.requireNonNull(product, "Product cannot be null");
        cartRepository.deleteProductFromCart(cartId, product);
    }

    public void emptyCart(UUID cartId) {
        Objects.requireNonNull(cartId, "Cart ID cannot be null");
        cartRepository.emptyCart(cartId);
    }

    public void deleteCartById(UUID cartId) {
        Objects.requireNonNull(cartId, "Cart ID cannot be null");
        cartRepository.deleteCartById(cartId);
    }
}