package com.example.service;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.example.model.Product;

import java.util.ArrayList;
import java.util.UUID;
@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {
ProductRepository productRepository;
    public ProductService(){
        productRepository = new ProductRepository();
    }
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }
    public ArrayList<Product> getProducts(){ return productRepository.getProducts(); }
    public Product getProductById(UUID productId){ return productRepository.getProductById(productId); }
    public Product updateProduct(UUID productId, String newName, double newPrice){ return productRepository.updateProduct(productId, newName, newPrice); }
    public void applyDiscount(double discount, ArrayList<UUID> productIds){ productRepository.applyDiscount(discount, productIds); }
    public void deleteProductById(UUID productId){ productRepository.deleteProductById(productId); }



}
