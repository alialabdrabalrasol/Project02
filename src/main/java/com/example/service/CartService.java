package com.example.service;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private ArrayList<Cart> cartlist=new ArrayList<>();
    private final ProductService productService;
    private final UserService userService;
    public ArrayList<Cart> getCartlist(){
        return cartlist;
    }



    public Integer addProduct(String cartID, String userID, String productID){
        Cart target_cart=findCart(cartID);
        if(target_cart==null){
            return -1;
        }
        User target_user=userService.getUser(userID);
        if(target_user==null){
            return 0;
        }
        Product target_product=productService.getProduct(productID);
        if(target_product==null){
            return 1;
        }
        target_cart.getProducts().add(target_product);
        return 2;

    }
    public Integer removeProduct(String cartID, String userID, String productID){
        Cart target_cart=findCart(cartID);
        if(target_cart==null){
            return -1;
        }
        User target_user=userService.getUser(userID);
        if(target_user==null){
            return 0;
        }
        Product target_product=productService.getProduct(productID);
        if(target_product==null){
            return 1;
        }
        target_cart.getProducts().remove(target_product);
        return 2;

    }
    public Cart findCart(String cartID){
        for (Cart curr_cart:cartlist
             ) {
            if(curr_cart.getId().equals(cartID)){
            return curr_cart;
            }
        }
        return null;
    }


}
