package com.example.controller;

import com.example.model.Product;
import com.example.model.ResponseApi;
import com.example.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity getProducts(){
        return ResponseEntity.status(200).body(productService.getProducts());
    }
    @PostMapping
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isProductAdded=productService.addProduct(product);
        if(!isProductAdded){
            return ResponseEntity.status(400).body(new ResponseApi("Product not added",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Product added",201));
    }
    @PutMapping("{product_id}")
    public ResponseEntity updateCategory(@RequestBody @Valid Product product , @PathVariable String product_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isProductUpdated=productService.updateProduct(product,product_id);
        if(!isProductUpdated){
            return ResponseEntity.status(400).body(new ResponseApi("Product not updated",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Product updated",201));

    }
    @DeleteMapping("{product_id}")
    public ResponseEntity deleteCategory(@PathVariable String product_id){
        Boolean isProductDeleted=productService.deleteProduct(product_id);
        if(!isProductDeleted){
            return ResponseEntity.status(400).body(new ResponseApi("Product not deleted",400));

        }
        return ResponseEntity.status(201).body(new ResponseApi("Product deleted",201));

    }
}
