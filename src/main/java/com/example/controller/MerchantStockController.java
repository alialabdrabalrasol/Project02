package com.example.controller;

import com.example.model.MerchantStock;
import com.example.model.ResponseApi;
import com.example.service.MerchantStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("api/v1/merchantstock")
@RequiredArgsConstructor
public class MerchantStockController {
    private final MerchantStockService merchantStockService;
    @GetMapping
    public ResponseEntity getMerchantStocks(){
        return ResponseEntity.status(200).body(merchantStockService.getMerchantStocks());
    }
    @PostMapping
    public ResponseEntity addMerchant(@RequestBody @Valid MerchantStock merchant, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isMerchantAdded=merchantStockService.addMerchantStock(merchant);
        if(!isMerchantAdded){
            return ResponseEntity.status(400).body(new ResponseApi("MerchantStock not added",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("MerchantStock added",201));
    }
    @PutMapping("{merchant_id}")
    public ResponseEntity updateMerchantStock(@RequestBody @Valid MerchantStock merchant , @PathVariable String merchant_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isMerchantUpdated=merchantStockService.updateMerchantStock(merchant,merchant_id);
        if(!isMerchantUpdated){
            return ResponseEntity.status(400).body(new ResponseApi("MerchantStock not updated",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("MerchantStock updated",201));

    }
    @DeleteMapping("{merchant_id}")
    public ResponseEntity deleteMerchantStock(@PathVariable String merchant_id){
        Boolean isMerchantDeleted=merchantStockService.deleteMerchantStock(merchant_id);
        if(!isMerchantDeleted){
            return ResponseEntity.status(400).body(new ResponseApi("MerchantStock not deleted",400));

        }
        return ResponseEntity.status(201).body(new ResponseApi("MerchantStock deleted",201));

    }
    @PostMapping("add")
    public ResponseEntity addStock(String merch_id,String product_id,Integer additional_stock){
        Integer addStockCase=merchantStockService.addProductStock(merch_id,product_id,additional_stock);
        switch (addStockCase){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid user id",400));
            case 1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid product id",400));
            case 2:
                return ResponseEntity.status(201).body(new ResponseApi("Stock added",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }
    }

}
