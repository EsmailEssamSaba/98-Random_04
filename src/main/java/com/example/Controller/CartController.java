package com.example.Controller;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/cart")

public class CartController {
    CartService cartService;

    public CartController() {this.cartService = new CartService();}

    @PostMapping("/")
    public Cart addCart(@RequestBody Cart cart){
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID());
        }
        return cartService.addCart(cart);
    }

    @GetMapping("/")
    public ArrayList<Cart> getCarts(){
        return cartService.getCarts();
    }

    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable UUID cartId){
        return cartService.getCartById(cartId);
    }

    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody Product product){
        cartService.addProductToCart(cartId, product);
       ;
        return "Product added to cart";
    }
    @PutMapping("/removeProduct/{cartId}")
    public String deleteProductFromCart(@PathVariable UUID cartId,  @RequestBody Product product) {

        cartService.deleteProductFromCart(cartId, product);
        return "Product deleted from cart";
    }
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId){
        cartService.deleteCartById(cartId);
        return "Cart deleted successfully";
    }

}