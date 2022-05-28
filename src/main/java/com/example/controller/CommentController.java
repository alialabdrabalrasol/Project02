package com.example.controller;

import com.example.model.Comment;
import com.example.model.ResponseApi;
import com.example.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @GetMapping
    public ResponseEntity getComments(){
        return ResponseEntity.status(200).body(commentService.getComments());
    }
    @PostMapping("post/{user_id}/{product_id}")
    public ResponseEntity postComment(@RequestBody @Valid Comment comment,@PathVariable String user_id,@PathVariable String product_id, Errors error){
        if(error.hasFieldErrors()){
            String err_msg=error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ResponseApi(err_msg,400));
        }
        Integer commentStatus=commentService.postComment(user_id,product_id,comment);
        switch (commentStatus){
            case -1:
                return ResponseEntity.status(400).body(new ResponseApi("User not found",400));
            case 0:
                return ResponseEntity.status(400).body(new ResponseApi("No product found with given product id",400));
            case 1:
                return ResponseEntity.status(201).body(new ResponseApi("Comment successfully posted",201));
            default:
                return ResponseEntity.status(500).body(new ResponseApi("Server Error",500));

        }

    }
    @GetMapping("{product_id}")
    public ResponseEntity getProductComments(@PathVariable String product_id){
        ArrayList<Comment>product_comments=commentService.getProductComments(product_id);
        if(product_comments==null){
            return ResponseEntity.status(400).body(new ResponseApi("Product comments not found",400));
        }
        return ResponseEntity.status(200).body(product_comments);
    }
    @GetMapping("rating/five")
    public ResponseEntity getFiveStarComments(){
        ArrayList<Comment>five_star_comments=commentService.getFiveStarComments();
        if(five_star_comments==null){
            return ResponseEntity.status(400).body(new ResponseApi("Product comments not found",400));
        }
        return ResponseEntity.status(200).body(five_star_comments);
    }
}
