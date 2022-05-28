package com.example.controller;

import com.example.model.Cart;
import com.example.model.ResponseApi;
import com.example.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @GetMapping
    public ResponseEntity getCarts(){
        return ResponseEntity.status(200).body(cartService.getCartlist());
    }
    @GetMapping("{cart_id}")
    public ResponseEntity getCart(@PathVariable String cart_id){
        Cart target_cart=cartService.findCart(cart_id);
        if(target_cart==null){
            return ResponseEntity.status(500).body(new ResponseApi("Invalid id",500));
        }
        return ResponseEntity.status(200).body(target_cart);
    }
    @PostMapping("add")
    public ResponseEntity addProduct(@RequestParam String cartID,@RequestParam String userID,@RequestParam String productID){
        Integer productAdditionCase=cartService.addProduct(cartID,userID,productID);
        switch (productAdditionCase){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid user id",400));
            case 1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid product id",400));
            case 2:
                return ResponseEntity.status(201).body(new ResponseApi("Product added",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }
    }
    @PutMapping("remove")
    public ResponseEntity removeProduct(@RequestParam String cartID,@RequestParam String userID,@RequestParam String productID){
        Integer productAdditionCase=cartService.removeProduct(cartID,userID,productID);
        switch (productAdditionCase){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid cart id",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid user id",400));
            case 1:
                return ResponseEntity.status(400).body(new ResponseApi("Invalid user id",400));
            case 2:
                return ResponseEntity.status(201).body(new ResponseApi("Product removed",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }
    }
}
