package com.example.service;

import com.example.model.Category;
import com.example.model.Product;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CategoryService {
    private ArrayList<Category> categorylist=new ArrayList<>();
    public ArrayList<Category> getCategories(){
        return categorylist;
    }
    public Boolean addCategory(Category category){
        return categorylist.add(category);
    }
    public Boolean updateCategory(Category category,String id){
        Integer target_category_Idx=findCategoryIdx(id);
        if(target_category_Idx==null){return false;}
        categorylist.set(target_category_Idx,category);
        return true;
    }
    public Boolean deleteCategory(String id){
        Integer target_category_Idx=findCategoryIdx(id);
        if(target_category_Idx==null){return false;}
        categorylist.remove(target_category_Idx);
        return true;
    }
    public Integer findCategoryIdx(String id){
        for (int i = 0; i <categorylist.size() ; i++) {
            if(categorylist.get(i).getID().equals(id)){
                return i;
            }
        }
        return null;
    }
}
