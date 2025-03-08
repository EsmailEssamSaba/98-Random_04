package com.example.MiniProject1.UnitTests;

import com.example.model.Product;
import com.example.model.User;
import com.example.model.Cart;
import com.example.model.Order;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import com.example.service.CartService;
import com.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartService cartService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private UserService userService;

    private User user;
    private UUID userId;
    private UUID orderId;
    private Order order;
    private Cart cart;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        orderId = UUID.randomUUID();
        user = new User();
        user.setId(userId);
        order = new Order(orderId, userId, 100.0, new ArrayList<>());
        cart = new Cart();
        cart.setId(userId);
        cart.setProducts(new ArrayList<>());
    }

    @Test
    void testAddUser_ValidUser() {
        userService.addUser(user);
        verify(userRepository, times(1)).addUser(user);
    }

    @Test
    void testAddUser_NullUser() {
        assertThrows(NullPointerException.class, () -> userService.addUser(null));
    }

    @Test
    void testAddUser_VerifyRepositoryCall() {
        userService.addUser(user);
        verify(userRepository, times(1)).addUser(user);
    }

    @Test
    void testGetUsers_ReturnsList() {
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.getUsers()).thenReturn(users);

        assertEquals(1, userService.getUsers().size());
    }

    @Test
    void testGetUsers_EmptyList() {
        when(userRepository.getUsers()).thenReturn(new ArrayList<>());
        assertTrue(userService.getUsers().isEmpty());
    }

    @Test
    void testGetUsers_VerifyCall() {
        userService.getUsers();
        verify(userRepository, times(1)).getUsers();
    }

    @Test
    void testGetUserById_ValidId() {
        when(userRepository.getUserById(userId)).thenReturn(user);
        assertEquals(user, userService.getUserById(userId));
    }

    @Test
    void testGetUserById_NullId() {
        assertThrows(NullPointerException.class, () -> userService.getUserById(null));
    }

    @Test
    void testGetUserById_NotFound() {
        when(userRepository.getUserById(userId)).thenReturn(null);
        assertNull(userService.getUserById(userId));
    }

    @Test
    void testAddOrderToUser_ValidCart() {
        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        cart.getProducts().add(new Product());

        userService.addOrderToUser(userId);

        verify(orderService, times(1)).addOrder(any(Order.class));
        verify(userRepository, times(1)).addOrderToUser(eq(userId), any(Order.class));
    }

    @Test
    void testAddOrderToUser_EmptyCart() {
        when(cartService.getCartByUserId(userId)).thenReturn(cart);
        assertThrows(IllegalArgumentException.class, () -> userService.addOrderToUser(userId));
    }

    @Test
    void testAddOrderToUser_NullUserId() {
        assertThrows(NullPointerException.class, () -> userService.addOrderToUser(null));
    }

    @Test
    void testRemoveOrderFromUser_ValidData() {
        userService.removeOrderFromUser(userId, orderId);
        verify(userRepository, times(1)).removeOrderFromUser(userId, orderId);
    }

    @Test
    void testRemoveOrderFromUser_NullUserId() {
        assertThrows(NullPointerException.class, () -> userService.removeOrderFromUser(null, orderId));
    }

    @Test
    void testRemoveOrderFromUser_NullOrderId() {
        assertThrows(NullPointerException.class, () -> userService.removeOrderFromUser(userId, null));
    }

    @Test
    void testDeleteUserById_ValidId() {
        userService.deleteUserById(userId);
        verify(userRepository, times(1)).deleteUserById(userId);
    }

    @Test
    void testDeleteUserById_NullId() {
        assertThrows(NullPointerException.class, () -> userService.deleteUserById(null));
    }

    @Test
    void testDeleteUserById_UserNotFound() {
        doThrow(new IllegalArgumentException("User not found")).when(userRepository).deleteUserById(userId);
        assertThrows(IllegalArgumentException.class, () -> userService.deleteUserById(userId));
    }
}
