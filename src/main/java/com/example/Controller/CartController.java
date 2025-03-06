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
        return ResponseEntity.ok("Product "+ product.getName() +" has been added to cart"+ cartService.getCartByUserId(cartId)+" successfully").toString();
    }
    @PutMapping("/removeProduct/{cartId}")
    public String deleteProductFromCart(@PathVariable UUID cartId,  @RequestBody Product product) {

        cartService.deleteProductFromCart(cartId, product);
        return ResponseEntity.ok("Product "+ product.getName() +" has been removed from cart successfully").toString();
    }
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId){
        cartService.deleteCartById(cartId);
        return ResponseEntity.ok("Cart "+ cartId +" has been removed successfully").toString();
    }

}