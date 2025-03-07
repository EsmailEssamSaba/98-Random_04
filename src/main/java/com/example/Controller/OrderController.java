package com.example.Controller;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.Order;
import com.example.service.CartService;
import com.example.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/order")

public class OrderController {
    OrderService orderService;
    public OrderController() {this.orderService = new OrderService();}

    @PostMapping("/")
    public void addOrder(@RequestBody Order order){
        orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId){
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/")
    public ArrayList<Order> getOrders(){
        return orderService.getOrders();
    }
    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId){
        orderService.deleteOrderById(orderId);
        return "Order deleted";
    }


}
