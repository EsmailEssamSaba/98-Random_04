package com.example.service;

import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.example.model.Product;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {
    ProductRepository productRepository;

    public ProductService() {
        productRepository = new ProductRepository();
    }

    public void addProduct(Product product) {
        Objects.requireNonNull(product, "Product cannot be null");
        productRepository.addProduct(product);
    }

    public ArrayList<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Product getProductById(UUID productId) {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        return productRepository.getProductById(productId);
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        Objects.requireNonNull(newName, "Product name cannot be null");

        if (newPrice < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }

        return productRepository.updateProduct(productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1");
        }
        Objects.requireNonNull(productIds, "Product IDs list cannot be null");

        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
        Objects.requireNonNull(productId, "Product ID cannot be null");
        productRepository.deleteProductById(productId);
    }
}
