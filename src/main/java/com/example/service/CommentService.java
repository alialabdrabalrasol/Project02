package com.example.service;

import com.example.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CommentService {
    private ArrayList<Comment> comments=new ArrayList<>();
    private final PurchaseHistoryService purchaseHistoryService;
    private final ProductService productService;
    private final UserService userService;
    public ArrayList<Comment> getComments(){
        return comments;
    }
    public Integer postComment(String user_id,String product_id,Comment comment){
        User target_user=userService.getUser(user_id);
        if(target_user==null){
            return -1;
        }
        boolean hasHistory= purchaseHistoryService.hasPurchaseHistory(user_id,product_id);
        if(!hasHistory)
        {
            return 0;
        }
        comments.add(comment);
            return 1;
    }
    public ArrayList<Comment> getProductComments(String product_id){
        Product target_product=productService.getProduct(product_id);
        if(target_product==null){
            return null;
        }
       return target_product.getComments();

    }
    public ArrayList<Comment>getFiveStarComments(){
        ArrayList<Comment>filtered_comments=new ArrayList<>();
        for (Comment curr_comment:comments
             ) {
            if(curr_comment.getRate()==5){
                filtered_comments.add(curr_comment);
            }

        }
        return filtered_comments;
    }
}
