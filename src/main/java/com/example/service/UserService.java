package com.example.service;
import com.example.model.Product;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.example.service.CartService;
import com.example.service.OrderService;
import com.example.model.User;
import com.example.model.Cart;
import com.example.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {
    UserRepository userRepository;
    CartService cartService;
    OrderService orderService;

    public UserService(UserRepository userRepository , CartService cartService,OrderService orderService ) {this.userRepository = userRepository; this.cartService = cartService;
        this.orderService = orderService;}
    public User addUser(User user){ userRepository.addUser(user); return user;}
    public ArrayList<User> getUsers(){return userRepository.getUsers();}
    public User getUserById(UUID id){return userRepository.getUserById(id);}
    public List<Order> getOrdersByUserId(UUID userId){
        return userRepository.getOrdersByUserId(userId);

    }
    public void emptyCart(UUID userId){
        cartService.emptyCart(userId);
    }
    public void addOrderToUser(UUID userId){
        Cart cart = cartService.getCartByUserId(userId);
        double Total=0;
        for(Product product:cart.getProducts()){
            Total+=product.getPrice();

        }
        Order order = new Order(UUID.randomUUID(),userId,Total,cart.getProducts());
        orderService.addOrder(order);
        userRepository.addOrderToUser(userId,order);
        emptyCart(userId);
        cartService.save(cart);




    }
    public void removeOrderFromUser(UUID userId, UUID orderId){
        userRepository.removeOrderFromUser(userId,orderId);
    }
    public void deleteUserById(UUID userId){
        userRepository.deleteUserById(userId);
    }





}
