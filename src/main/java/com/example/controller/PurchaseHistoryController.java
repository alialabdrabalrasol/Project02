package com.example.controller;

import com.example.service.PurchaseHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/purchasehistory")
@RequiredArgsConstructor
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;
    @GetMapping
    public ResponseEntity getPurchaseHistory(){
        return ResponseEntity.status(200).body(purchaseHistoryService.getPurchaseHistory());
    }

}
