package com.example.controller;

import com.example.model.Merchant;
import com.example.model.ResponseApi;
import com.example.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;
    @GetMapping
    public ResponseEntity getMerchants(){
        return ResponseEntity.status(200).body(merchantService.getMerchants());
    }
    @PostMapping
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isMerchantAdded=merchantService.addMerchant(merchant);
        if(!isMerchantAdded){
            return ResponseEntity.status(400).body(new ResponseApi("Merchant not added",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Merchant added",201));
    }
    @PutMapping("{merchant_id}")
    public ResponseEntity updateMerchant(@RequestBody @Valid Merchant merchant , @PathVariable String merchant_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isMerchantUpdated=merchantService.updateMerchant(merchant,merchant_id);
        if(!isMerchantUpdated){
            return ResponseEntity.status(400).body(new ResponseApi("Merchant not updated",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Merchant updated",201));

    }
    @DeleteMapping("{merchant_id}")
    public ResponseEntity deleteMerchant(@PathVariable String merchant_id){
        Boolean isMerchantDeleted=merchantService.deleteMerchant(merchant_id);
        if(!isMerchantDeleted){
            return ResponseEntity.status(400).body(new ResponseApi("Merchant not deleted",400));

        }
        return ResponseEntity.status(201).body(new ResponseApi("Merchant deleted",201));

    }
}
