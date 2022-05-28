package com.example.controller;

import com.example.model.Cart;
import com.example.model.PurchaseHistory;
import com.example.model.ResponseApi;
import com.example.model.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }
    @PostMapping
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isUserAdded=userService.addUser(user);
        if(!isUserAdded){
            return ResponseEntity.status(400).body(new ResponseApi("User not added",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("User added",201));
    }
    @PutMapping("{user_id}")
    public ResponseEntity updateUser(@RequestBody @Valid User user,@PathVariable String user_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isUserUpdated=userService.updateUser(user,user_id);
        if(!isUserUpdated){
            return ResponseEntity.status(400).body(new ResponseApi("User not updated",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("User updated",201));

    }
    @DeleteMapping("{user_id}")
    public ResponseEntity deleteUser(@PathVariable String user_id){
        Boolean isUserDeleted=userService.deleteUser(user_id);
        if(!isUserDeleted){
            return ResponseEntity.status(400).body(new ResponseApi("User not deleted",400));

        }
        return ResponseEntity.status(201).body(new ResponseApi("User deleted",201));

    }
    @PostMapping("buy/wocart")
    public  ResponseEntity buyWithoutCart(@RequestParam String user_id,@RequestParam String product_id,@RequestParam String merch_id){
        Integer buyStatus=userService.buyWithoutCart(user_id,product_id,merch_id);
        switch (buyStatus){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("Product out of stock",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("Balance is not enough",400));
            case 1:
                return ResponseEntity.status(201).body(new ResponseApi("Product successfully bought",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }
    }
    @PostMapping("buy/wcart")
    public  ResponseEntity buyWithCart(@RequestBody @Valid Cart cart,Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }

        Integer buyStatus=userService.buyWithCart(cart);
        switch (buyStatus){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("Product out of stock",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("Balance is not enough",400));
            case 1:
                return ResponseEntity.status(201).body(new ResponseApi("Product successfully bought",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }
    }
    @GetMapping("history")
    public ResponseEntity getPurchaseHistory(@RequestParam String user_id){
        ArrayList<PurchaseHistory>user_purchase_history=userService.getHistory(user_id);
        if(user_purchase_history==null){
            return ResponseEntity.status(400).body(new ResponseApi("User not found",400));
        }
        return ResponseEntity.status(200).body(user_purchase_history);
    }

}
