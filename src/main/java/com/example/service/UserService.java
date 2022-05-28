package com.example.service;

import com.example.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    private ArrayList<User> users=new ArrayList<>();
    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final PurchaseHistoryService purchaseHistoryService;
    public User getUser(String userID) {
        return users.get(findUserIdx(userID));
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public Boolean addUser(User user){
        return users.add(user);
    }
    public Boolean updateUser(User user,String id){
        Integer target_user_Idx=findUserIdx(id);
        if(target_user_Idx==null){return false;}
        users.set(target_user_Idx,user);
        return true;
    }
    public Boolean deleteUser(String id){
        Integer target_user_Idx=findUserIdx(id);
        if(target_user_Idx==null){return false;}
        users.remove(target_user_Idx);
        return true;
    }
    public Integer buyWithoutCart(String user_id,String product_id,String merch_id){
        MerchantStock target_merch=merchantStockService.findMerchantStock(merch_id);
        if(target_merch.getStock()<1){
            return -1;
        }
        User target_user=getUser(user_id);
        Product target_product=productService.getProduct(product_id);
        if(target_user.getBalance()<target_product.getPrice()){
            return 0;
        }
        target_merch.setStock(target_merch.getStock()-1);

        target_user.setBalance(target_user.getBalance()-target_product.getPrice());
        //add to purchase history
        purchaseHistoryService.addPurchaseHistory(user_id,product_id,target_product.getPrice());
        return 1;
    }

    public Integer buyWithCart(Cart cart){
        User curr_user= users.get(findUserIdx(cart.getUserId()));
        Integer curr_user_balance=curr_user.getBalance();
        int total=0;
        for (Product item:cart.getProducts()
             ) {
           Integer curr_merch_stock=merchantStockService.getStock(item.getID());
           if(curr_merch_stock<1){
               return -1;
           }
                    total+=item.getPrice();
        }
        if(curr_user_balance<total){
            return 0;
        }

        for (Product item:cart.getProducts()
        ) {
            for (MerchantStock curr_merch_stock: merchantStockService.getMerchantStocks()
                 ) {
                if(curr_merch_stock.getProductId().equals(item.getID())){
                    curr_merch_stock.setStock(curr_merch_stock.getStock()-1);
                }
            }

            purchaseHistoryService.addPurchaseHistory(cart.getUserId(), item.getID(),item.getPrice());
        }

       curr_user.setBalance(curr_user_balance-total);
        return 1;
    }
    public ArrayList<PurchaseHistory>getHistory(String user_id){
        User target_user=getUser(user_id);
        if(target_user==null){
            return null;
        }
        ArrayList<PurchaseHistory>user_history=new ArrayList<>();
        for (PurchaseHistory item:purchaseHistoryService.getPurchaseHistory()
        ) {
            if(item.getUserId().equals(user_id)){
                user_history.add(item);
            }
        }
        return user_history;
    }
    public Integer findUserIdx(String id){
        for (int i = 0; i <users.size() ; i++) {
            if(users.get(i).getId().equals(id)){
                return i;
            }
        }
        return null;
    }


}
