package com.example.MiniProject1.UnitTests;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
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
class ProductServiceTests {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private UUID productId;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        product = new Product();
        product.setId(productId);
    }

    @Test
    void testAddProduct_ValidProduct() {
        productService.addProduct(product);
        verify(productRepository, times(1)).addProduct(product);
    }

    @Test
    void testAddProduct_NullProduct() {
        assertThrows(NullPointerException.class, () -> productService.addProduct(null));
    }

    @Test
    void testAddProduct_VerifyRepositoryCall() {
        productService.addProduct(product);
        verify(productRepository, times(1)).addProduct(product);
    }

    @Test
    void testGetProducts_ReturnsList() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.getProducts()).thenReturn(products);

        assertEquals(1, productService.getProducts().size());
    }

    @Test
    void testGetProducts_EmptyList() {
        when(productRepository.getProducts()).thenReturn(new ArrayList<>());
        assertTrue(productService.getProducts().isEmpty());
    }

    @Test
    void testGetProducts_VerifyCall() {
        productService.getProducts();
        verify(productRepository, times(1)).getProducts();
    }

    @Test
    void testGetProductById_ValidId() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        assertEquals(product, productService.getProductById(productId));
    }

    @Test
    void testGetProductById_NullId() {
        assertThrows(NullPointerException.class, () -> productService.getProductById(null));
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.getProductById(productId)).thenReturn(null);
        assertNull(productService.getProductById(productId));
    }

    @Test
    void testUpdateProduct_ValidData() {
        when(productRepository.updateProduct(productId, "New Name", 99.99))
                .thenReturn(new Product());

        assertNotNull(productService.updateProduct(productId, "New Name", 99.99));
    }

    @Test
    void testUpdateProduct_NullName() {
        assertThrows(NullPointerException.class, () -> productService.updateProduct(productId, null, 10.0));
    }

    @Test
    void testUpdateProduct_NegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(productId, "Valid Name", -10.0));
    }

    @Test
    void testApplyDiscount_ValidDiscount() {
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(productId);

        productService.applyDiscount(0.2, productIds);
        verify(productRepository, times(1)).applyDiscount(0.2, productIds);
    }

    @Test
    void testApplyDiscount_NegativeDiscount() {
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(productId);

        assertThrows(IllegalArgumentException.class, () -> productService.applyDiscount(-0.5, productIds));
    }

    @Test
    void testApplyDiscount_NullProductIds() {
        assertThrows(NullPointerException.class, () -> productService.applyDiscount(0.2, null));
    }

    @Test
    void testDeleteProductById_ValidId() {
        productService.deleteProductById(productId);
        verify(productRepository, times(1)).deleteProductById(productId);
    }

    @Test
    void testDeleteProductById_NullId() {
        assertThrows(NullPointerException.class, () -> productService.deleteProductById(null));
    }

    @Test
    void testDeleteProductById_VerifyCall() {
        productService.deleteProductById(productId);
        verify(productRepository, times(1)).deleteProductById(productId);
    }
}
