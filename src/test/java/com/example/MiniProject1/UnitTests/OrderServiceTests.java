package com.example.MiniProject1.UnitTests;

import com.example.model.Order;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTests {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new Order();
        order.setId(orderId);
    }

    @Test
    void testAddOrder_ValidOrder() {
        orderService.addOrder(order);
        verify(orderRepository, times(1)).addOrder(order);
    }

    @Test
    void testAddOrder_GeneratesIdIfNull() {
        Order newOrder = new Order(); // No ID
        orderService.addOrder(newOrder);
        assertNotNull(newOrder.getId()); // OrderService should set an ID
    }

    @Test
    void testAddOrder_NullOrder() {
        assertThrows(NullPointerException.class, () -> orderService.addOrder(null));
    }

    @Test
    void testGetOrders_ReturnsList() {
        ArrayList<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderRepository.getOrders()).thenReturn(orders);

        assertEquals(1, orderService.getOrders().size());
    }

    @Test
    void testGetOrders_EmptyList() {
        when(orderRepository.getOrders()).thenReturn(new ArrayList<>());
        assertTrue(orderService.getOrders().isEmpty());
    }

    @Test
    void testGetOrders_VerifyCall() {
        orderService.getOrders();
        verify(orderRepository, times(1)).getOrders();
    }

    @Test
    void testGetOrderById_ValidId() {
        when(orderRepository.getOrderById(orderId)).thenReturn(order);
        assertEquals(order, orderService.getOrderById(orderId));
    }

    @Test
    void testGetOrderById_NullId() {
        assertThrows(NullPointerException.class, () -> orderService.getOrderById(null));
    }

    @Test
    void testGetOrderById_NotFound() {
        when(orderRepository.getOrderById(orderId)).thenReturn(null);
        assertNull(orderService.getOrderById(orderId));
    }

    @Test
    void testDeleteOrderById_ValidId() {
        orderService.deleteOrderById(orderId);
        verify(orderRepository, times(1)).deleteOrderById(orderId);
    }

    @Test
    void testDeleteOrderById_NullId() {
        assertThrows(NullPointerException.class, () -> orderService.deleteOrderById(null));
    }

    @Test
    void testDeleteOrderById_ExceptionHandling() {
        doThrow(new RuntimeException("Database error")).when(orderRepository).deleteOrderById(orderId);
        assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(orderId));
    }
}
