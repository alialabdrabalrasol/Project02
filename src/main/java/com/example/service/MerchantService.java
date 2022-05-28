package com.example.service;

import com.example.model.Category;
import com.example.model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {

    private ArrayList<Merchant> merchantlist=new ArrayList<>();
    public ArrayList<Merchant> getMerchants(){
        return merchantlist;
    }
    public Boolean addMerchant(Merchant merchant){
        return merchantlist.add(merchant);
    }
        public Boolean updateMerchant(Merchant merchant,String id){
        Integer target_merchant_Idx=findMerchantIdx(id);
        if(target_merchant_Idx==null){return false;}
        merchantlist.set(target_merchant_Idx,merchant);
        return true;
    }
    public Boolean deleteMerchant(String id){
        Integer target_merchant_Idx=findMerchantIdx(id);
        if(target_merchant_Idx==null){return false;}
        merchantlist.remove(target_merchant_Idx);
        return true;
    }
    public Integer findMerchantIdx(String id){
        for (int i = 0; i <merchantlist.size() ; i++) {
            if(merchantlist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }
}
