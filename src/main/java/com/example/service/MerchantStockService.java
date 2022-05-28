package com.example.service;


import com.example.model.MerchantStock;
import com.example.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MerchantStockService {

    private ArrayList<MerchantStock> merchantstocklist=new ArrayList<>();
    private final ProductService productService;

    public Integer getStock(String product_id){
        for (MerchantStock curr_merch:merchantstocklist){
            if (curr_merch.getProductId().equals(product_id)){
                return curr_merch.getStock();
            }
        }

        return null;
    }

    public ArrayList<MerchantStock> getMerchantStocks(){
        return merchantstocklist;
    }
    public Boolean addMerchantStock(MerchantStock merchantStock){
        return merchantstocklist.add(merchantStock);
    }
    public Boolean updateMerchantStock(MerchantStock merchantStock,String id){
        Integer target_merchantStock_Idx=findMerchantStockIdx(id);
        if(target_merchantStock_Idx==null){return false;}
        merchantstocklist.set(target_merchantStock_Idx,merchantStock);
        return true;
    }
    public Boolean deleteMerchantStock(String id){
        Integer target_merchantStock_Idx=findMerchantStockIdx(id);
        if(target_merchantStock_Idx==null){return false;}
        merchantstocklist.remove(target_merchantStock_Idx);
        return true;
    }
    public Integer addProductStock(String merch_id,String product_id,Integer additional_stock){
      MerchantStock target_merch=findMerchantStock(merch_id);
      if(target_merch==null){
          return -1;
      }
        Product target_product=productService.getProduct(product_id);
      if(target_product==null){
          return 0;
      }
      target_merch.setStock(target_merch.getStock()+additional_stock);
      return 1;
    }
    public MerchantStock findMerchantStock(String id){
        for (int i = 0; i <merchantstocklist.size() ; i++) {
            if(merchantstocklist.get(i).getID().equals(id)){
                return merchantstocklist.get(i);
            }
        }
        return null;
    }
    public Integer findMerchantStockIdx(String id){
        for (int i = 0; i <merchantstocklist.size() ; i++) {
            if(merchantstocklist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }
}
