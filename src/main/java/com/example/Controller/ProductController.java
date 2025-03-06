package com.example.Controller;

import com.example.model.Product;

import com.example.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/product")

public class ProductController {
    ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @PostMapping("/")
    public Product addProduct(@RequestBody Product product){
        if (product.getId() == null) {
            product.setId(UUID.randomUUID());
        }
     productService.addProduct(product);
     return product;
    }
    @GetMapping("/")
    public ArrayList<Product> getProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId){
        return productService.getProductById(productId);
    }
    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String,Object> body){
        String newName = (String) body.get("name");
        double newPrice = ((Number) body.get("price")).doubleValue();
        return   productService.updateProduct(productId, newName, newPrice);

    }
    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount,@RequestBody ArrayList<UUID>productIds){
        productService.applyDiscount(discount, productIds);
        return ResponseEntity.ok("Products  has been discounted successfully").toString();
    }
    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId){
        productService.deleteProductById(productId);
        return ResponseEntity.ok("Product "+ productId+" has been deleted successfully").toString();
    }




}
