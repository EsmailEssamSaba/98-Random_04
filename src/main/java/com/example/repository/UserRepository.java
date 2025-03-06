package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;
import com.example.model.User;
@Repository
@SuppressWarnings("rawtypes")
public class UserRepository  extends MainRepository<User> {
    public UserRepository() {}
    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/users.json";
    }
    protected Class<User[]> getArrayType() {return User[].class;}

    public ArrayList<User> getUsers(){return super.findAll();}
    public User getUserById(UUID userId){
            ArrayList<User> users = super.findAll();
            for (User user : users) {
                if(
                        user.getId().equals(userId)
                ){
                    return user;
                }
            }
            return null;
    }
    public User addUser(User user){
       super.save(user); return user;
    }
    public List<Order> getOrdersByUserId(UUID userId){
        ArrayList<User> users = super.findAll();
        for (User user : users) {
            if(
                    user.getId().equals(userId)
            ){
                return user.getOrders();

            }
        }
        return null;
    }
    public void addOrderToUser(UUID userId, Order order){
        ArrayList<User> users = super.findAll();
        for (User user : users) {
            if ( user.getId().equals(userId)) {
                order.setUserId(userId);
                user.getOrders().add(order);
                super.saveAll(users);
                break;

            }
        }
    }
    public void removeOrderFromUser(UUID userId, UUID orderId){
        ArrayList<User> users = super.findAll();
        for (User user : users) {
            if ( user.getId().equals(userId)) {
                user.getOrders().removeIf(order -> order.getId().equals(orderId));
                super.saveAll(users);
                break;
            }
        }
    }
    public void deleteUserById(UUID userId){
        ArrayList<User> users = super.findAll();
        for (User user : users) {
            if ( user.getId().equals(userId)) {
                users.remove(user);
                super.saveAll(users);
                break;
            }
        }
    }





}
