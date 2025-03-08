package com.example.MiniProject1.UnitTests;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import com.example.service.CartService;
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
class CartServiceTests {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart cart;
    private UUID cartId;
    private UUID userId;
    private Product product;

    @BeforeEach
    void setUp() {
        cartId = UUID.randomUUID();
        userId = UUID.randomUUID();
        cart = new Cart();
        cart.setId(cartId);
        product = new Product();
    }

    @Test
    void testSaveCart() {
        cartService.save(cart);
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testSaveCart_NullCart() {
        assertThrows(NullPointerException.class, () -> cartService.save(null));
    }

    @Test
    void testSaveCart_MultipleSaves() {
        cartService.save(cart);
        cartService.save(cart);
        verify(cartRepository, times(2)).save(cart);
    }

    @Test
    void testAddCart() {
        when(cartRepository.addCart(cart)).thenReturn(cart);
        Cart result = cartService.addCart(cart);
        assertEquals(cart, result);
    }

    @Test
    void testAddCart_NullCart() {
        assertThrows(NullPointerException.class, () -> cartService.addCart(null));
    }

    @Test
    void testAddCart_VerifyRepositoryCall() {
        cartService.addCart(cart);
        verify(cartRepository, times(1)).addCart(cart);
    }

    @Test
    void testGetCartById_ValidId() {
        when(cartRepository.getCartById(cartId)).thenReturn(cart);
        Cart result = cartService.getCartById(cartId);
        assertEquals(cart, result);
    }

    @Test
    void testGetCartById_NullId() {
        assertThrows(NullPointerException.class, () -> cartService.getCartById(null));
    }

    @Test
    void testGetCartById_CartNotFound() {
        when(cartRepository.getCartById(cartId)).thenReturn(null);
        assertNull(cartService.getCartById(cartId));
    }

    @Test
    void testGetCarts_ReturnsList() {
        ArrayList<Cart> carts = new ArrayList<>();
        carts.add(cart);
        when(cartRepository.getCarts()).thenReturn(carts);
        assertEquals(1, cartService.getCarts().size());
    }

    @Test
    void testGetCarts_EmptyList() {
        when(cartRepository.getCarts()).thenReturn(new ArrayList<>());
        assertTrue(cartService.getCarts().isEmpty());
    }

    @Test
    void testGetCarts_VerifyCall() {
        cartService.getCarts();
        verify(cartRepository, times(1)).getCarts();
    }

    @Test
    void testGetCartByUserId_ValidUser() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(cart);
        assertEquals(cart, cartService.getCartByUserId(userId));
    }

    @Test
    void testGetCartByUserId_NullUser() {
        assertThrows(NullPointerException.class, () -> cartService.getCartByUserId(null));
    }

    @Test
    void testGetCartByUserId_NotFound() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(null);
        assertNull(cartService.getCartByUserId(userId));
    }

    @Test
    void testAddProductToCart_ValidProduct() {
        cartService.addProductToCart(cartId, product);
        verify(cartRepository, times(1)).addProductToCart(cartId, product);
    }

    @Test
    void testAddProductToCart_NullProduct() {
        assertThrows(NullPointerException.class, () -> cartService.addProductToCart(cartId, null));
    }

    @Test
    void testAddProductToCart_InvalidCartId() {
        assertThrows(NullPointerException.class, () -> cartService.addProductToCart(null, product));
    }

    @Test
    void testDeleteProductFromCart_ValidProduct() {
        cartService.deleteProductFromCart(cartId, product);
        verify(cartRepository, times(1)).deleteProductFromCart(cartId, product);
    }

    @Test
    void testDeleteProductFromCart_NullProduct() {
        assertThrows(NullPointerException.class, () -> cartService.deleteProductFromCart(cartId, null));
    }

    @Test
    void testDeleteProductFromCart_NonExistingCart() {
        assertThrows(NullPointerException.class, () -> cartService.deleteProductFromCart(null, product));
    }

    @Test
    void testEmptyCart_ValidCart() {
        cartService.emptyCart(cartId);
        verify(cartRepository, times(1)).emptyCart(cartId);
    }

    @Test
    void testEmptyCart_NullId() {
        assertThrows(NullPointerException.class, () -> cartService.emptyCart(null));
    }

    @Test
    void testEmptyCart_VerifyCall() {
        cartService.emptyCart(cartId);
        verify(cartRepository, times(1)).emptyCart(cartId);
    }

    @Test
    void testDeleteCartById_ValidCartId() {
        cartService.deleteCartById(cartId);
        verify(cartRepository, times(1)).deleteCartById(cartId);
    }

    @Test
    void testDeleteCartById_NullCartId() {
        assertThrows(NullPointerException.class, () -> cartService.deleteCartById(null));
    }

    @Test
    void testDeleteCartById_MultipleDeletes() {
        cartService.deleteCartById(cartId);
        cartService.deleteCartById(cartId);
        verify(cartRepository, times(2)).deleteCartById(cartId);
    }
}






