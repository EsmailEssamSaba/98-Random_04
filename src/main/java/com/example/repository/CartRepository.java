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
public class CartRepository extends MainRepository<Cart> {
    public CartRepository(){
    }
    @Override

    protected String getDataPath() {
        return "src/main/java/com/example/data/carts.json";
    }


    protected Class<Cart[]> getArrayType() {
        return Cart[].class;
    }
    public Cart addCart(Cart cart){ super.save(cart); return cart; }



    public ArrayList<Cart> getCarts() {
        return super.findAll();
    }
    public Cart getCartById(UUID cartId){
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if(cart.getId().equals(cartId)){
                return cart;

            }
        }
        return null;
    }
    public Cart getCartByUserId(UUID userId){
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if(cart.getUserId().equals(userId)){
                return cart;
            }

        }
        return null;
    }
    public void addProductToCart(UUID cartId, Product product){
        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.getProducts().add(product);
                super.saveAll(carts);

                break;
            }
        }
    }
    public void deleteProductFromCart(UUID cartId, Product product){

        ArrayList<Cart> carts = getCarts();
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.getProducts().removeIf(p -> p.getId().equals(product.getId()));
               super.saveAll(carts);

                break;
            }
        }
    }
    public void deleteCartById(UUID cartId){

        ArrayList<Cart> carts = getCarts();

        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
               carts.remove(cart);
                super.saveAll(carts);

                break;
            }
        }

    }


}