package com.example.Controller;
import com.example.model.CartModel;
import com.example.model.ProductModel;
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
    public CartModel addCart(@RequestBody CartModel cart){
        return cartService.addCart(cart);
    }

    @GetMapping("/")
    public ArrayList<CartModel> getCarts(){
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public CartModel getCartById(@PathVariable UUID cartId){
        return cartService.getCartById(cartId);
    }

    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody ProductModel product){
        cartService.addProductToCart(cartId, product);
        return ResponseEntity.ok("Product "+ product.getName() +" has been added to cart successfully").toString();
    }
    @PutMapping("/removeProduct/{userId}")
    public String deleteProductFromCart(@PathVariable UUID userId,  @RequestBody ProductModel product) {

        cartService.deleteProductFromCart(userId, product);
        return ResponseEntity.ok("Product "+ product.getName() +" has been removed from cart successfully").toString();
    }
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId){
        cartService.deleteCartById(cartId);
        return ResponseEntity.ok("Cart "+ cartId +" has been removed successfully").toString();
    }

}