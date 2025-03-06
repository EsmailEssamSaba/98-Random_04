package com.example.Controller;

import com.example.model.Order;
import com.example.model.User;
import com.example.model.Cart;
import com.example.model.Product;

import com.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.CartService;
import com.example.service.ProductService;
import com.example.service.OrderService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
@RestController
@RequestMapping("/user")
public class UserController {
    UserService userService;
    CartService cartService;
    ProductService productService;
    OrderService orderService;
    public UserController(UserService userService, CartService cartService , ProductService productService, OrderService orderService) {
        this.cartService=cartService;
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }
    @PostMapping("/")
    public User addUser(@RequestBody User user){
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        return userService.addUser(user);
    }
    @GetMapping("/")
    public ArrayList<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable UUID userId){
        return userService.getUserById(userId);
    }
    @GetMapping("/{userId}/orders")
    public List<Order> getOrdersByUserId(@PathVariable UUID userId){
        return userService.getOrdersByUserId(userId);

    }
    @PostMapping("/{userId}/checkout")
    public String addOrderToUser(@PathVariable UUID userId){
        userService.addOrderToUser(userId);
      User x=  userService.getUserById(userId);
         userService.emptyCart(userId);


        return ResponseEntity.ok("Order has been added to user "+ x.getName()+" successfully").toString();

    }

    @PostMapping("/{userId}/removeOrder")
    public String removeOrderFromUser(@PathVariable UUID userId, @RequestParam UUID orderId){
        userService.removeOrderFromUser(userId, orderId);
        return ResponseEntity.ok("Order has been removed from "+userId+"successfully").toString();
    }
    @DeleteMapping("/{userId}/emptyCart")
    public String emptyCart(@PathVariable UUID userId){
        userService.emptyCart(userId);
        return ResponseEntity.ok("Cart is empty").toString();
    }
    @PutMapping("/addProductToCart")
    public String addProductToCart(@RequestParam UUID userId, @RequestParam UUID productId){
        if(cartService.getCartByUserId(userId)==null){
            cartService.addCart(new Cart(UUID.randomUUID(),userId, new ArrayList<>()));

        }
        Cart cart = cartService.getCartByUserId(userId);
        if (cart == null) {
            return ResponseEntity.notFound().toString();
        }


        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.notFound().toString();
        }
        cartService.addProductToCart(cart.getId(), product);

        return ResponseEntity.ok("Product "+ productService.getProductById(productId).getName()+" has been added to "+cart.getId() +" successfully").toString();

    }
    @PutMapping("/deleteProductFromCart")
    public String deleteProductFromCart(@RequestParam UUID userId, @RequestParam UUID productId){
        cartService.deleteProductFromCart(userId,productService.getProductById(productId));
        return ResponseEntity.ok("Product "+ productId+" has been deleted successfully").toString();
    }
    @DeleteMapping("/delete/{userId}")
    public String deleteUserById(@PathVariable UUID userId){
        userService.deleteUserById(userId);
        return ResponseEntity.ok("User has been deleted successfully").toString();
    }

}
