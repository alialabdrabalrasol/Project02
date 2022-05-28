package com.example.controller;

import com.example.model.Category;
import com.example.model.ResponseApi;
import com.example.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryContoller {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity getCategories(){
        return ResponseEntity.status(200).body(categoryService.getCategories());
    }
    @PostMapping
    public ResponseEntity addCategory(@RequestBody @Valid Category category, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isCategoryAdded=categoryService.addCategory(category);
        if(!isCategoryAdded){
            return ResponseEntity.status(400).body(new ResponseApi("Category not added",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Category added",201));
    }
    @PutMapping("{category_id}")
    public ResponseEntity updateCategory(@RequestBody @Valid Category category , @PathVariable String category_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Boolean isCategoryUpdated=categoryService.updateCategory(category,category_id);
        if(!isCategoryUpdated){
            return ResponseEntity.status(400).body(new ResponseApi("Category not updated",400));
        }
        return ResponseEntity.status(201).body(new ResponseApi("Category updated",201));

    }
    @DeleteMapping("{category_id}")
    public ResponseEntity deleteCategory(@PathVariable String category_id){
        Boolean isCategoryDeleted=categoryService.deleteCategory(category_id);
        if(!isCategoryDeleted){
            return ResponseEntity.status(400).body(new ResponseApi("Category not deleted",400));

        }
        return ResponseEntity.status(201).body(new ResponseApi("Category deleted",201));

    }
}
