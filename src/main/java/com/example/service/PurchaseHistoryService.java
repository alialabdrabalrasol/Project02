package com.example.service;


import com.example.model.PurchaseHistory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {
    private ArrayList<PurchaseHistory> purchaseHistorylist=new ArrayList<>();

    public ArrayList<PurchaseHistory> getPurchaseHistory(){
        return purchaseHistorylist;
    }
    public Boolean addPurchaseHistory(String user_id,String product_id,Integer price){
     PurchaseHistory purchaseHistory=new PurchaseHistory(Math.random()+"",user_id,product_id,price);
        return purchaseHistorylist.add(purchaseHistory);
    }
    public Boolean hasPurchaseHistory(String user_id,String product_id){
        boolean hasHistory=false;
        for (PurchaseHistory item:purchaseHistorylist) {
            if(item.getUserId().equals(user_id)&&item.getProductId().equals(product_id)){
                hasHistory=true;
            }
        }
        return hasHistory;
    }


}
