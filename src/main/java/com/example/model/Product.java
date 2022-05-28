package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;

@Data
public class Product {
    @NotEmpty(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotEmpty(message = "name can't be empty")
    @Length( min = 3,message = "name has to be 3 char at least")
    private String name;
    @NotNull(message = "price can't be empty")
    @Positive(message = "price has to be greater than 0")
    private Integer price;
    @NotEmpty(message = "category id can't be empty")
    @Length( min = 3,message = "category id has to be 3 char at least")
    private String categoryID;
    private ArrayList<Comment>comments;

    public Product(String ID, String name, int price, String categoryID) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.categoryID = categoryID;
        this.comments=new ArrayList<>();
    }
}
