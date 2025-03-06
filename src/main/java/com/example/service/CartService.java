package com.example.service;
import com.example.repository.CartRepository;
import org.springframework.stereotype.Service;
import com.example.model.Cart;
import com.example.model.Product;

import java.util.ArrayList;
import java.util.UUID;
@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart>{
    CartRepository cartRepository;

    public CartService() {
        this.cartRepository = new CartRepository();
    }
    public void save(Cart cart) {
        cartRepository.save(cart);
    }
    public Cart addCart(Cart cart){
        return cartRepository.addCart(cart);
    }
    public Cart getCartById(UUID cartId){
       return cartRepository.getCartById(cartId);

    }
    public ArrayList<Cart> getCarts(){
        return cartRepository.getCarts();
    }
    public Cart getCartByUserId(UUID userId){
        return cartRepository.getCartByUserId(userId);
    }
    public void addProductToCart(UUID cartId, Product product){
         cartRepository.addProductToCart(cartId,product);
    }
    public void deleteProductFromCart(UUID cartId, Product product){
        cartRepository.deleteProductFromCart(cartId,product);
    }
    public void emptyCart(UUID cartId){
        cartRepository.emptyCart(cartId);
    }
    public void deleteCartById(UUID cartId){
        cartRepository.deleteCartById(cartId);
    }

}
