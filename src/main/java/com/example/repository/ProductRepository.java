package com.example.repository;
import com.example.model.Cart;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import com.example.model.Product;
@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    public ProductRepository() {}
    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/products.json";
    }
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }
    public Product addProduct(Product product){
        super.save(product);
        return product;
    }
    public ArrayList<Product> getProducts(){return super.findAll();}
    public Product getProductById(UUID productId){
        ArrayList<Product> products = getProducts();
        for (Product product : products) {
            if(product.getId().equals(productId)){
                return product;
            }

        }
        return null;
    }
            public Product updateProduct(UUID productId, String newName, double newPrice){
                ArrayList<Product> products = getProducts();
                for (Product product : products) {
                    if (productId.equals(product.getId())) {
                        product.setName(newName);
                        product.setPrice(newPrice);
                        super.saveAll(products);
                        break;


                    }

                }


                 return getProductById(productId);
            }
    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        ArrayList<Product> products = getProducts();


        for (Product product : products) {
            if (productIds.contains(product.getId())) {
                double newPrice = product.getPrice() * (1 - discount / 100);


                product.setPrice(newPrice);
            }
        }

        saveAll(products);
    }
    public void deleteProductById(UUID productId){
        ArrayList<Product> products = getProducts();
        for (Product product : products) {
            if (productId.equals(product.getId())) {
                products.remove(product);
                break;

            }
            super.saveAll(products);
        }
    }




}