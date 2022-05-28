package com.example.service;

import com.example.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductService {
    private ArrayList<Product>products=new ArrayList<>();
    public Product getProduct(String productID) {
        return products.get(findProductIdx(productID));
    }
    public ArrayList<Product> getProducts(){
        return products;
    }
    public Boolean addProduct(Product product){
       return products.add(product);
    }
    public Boolean updateProduct(Product product,String id){
    Integer target_product_Idx=findProductIdx(id);
    if(target_product_Idx==null){return false;}
    products.set(target_product_Idx,product);
    return true;
    }
    public Boolean deleteProduct(String id){
        Integer target_product_Idx=findProductIdx(id);
        if(target_product_Idx==null){return false;}
        products.remove(target_product_Idx);
        return true;
    }

    public Integer findProductIdx(String id){
        for (int i = 0; i <products.size() ; i++) {
           if(products.get(i).getID().equals(id)){
               return i;
           }
        }
        return null;
    }


}
