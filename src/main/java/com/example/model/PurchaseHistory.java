package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class PurchaseHistory {
    @NotEmpty(message = "id can't be empty")
    @Size(min = 3,message = "id has to be at least 3 char")
    private String id;
    @NotEmpty(message = "user id can't be empty")
    @Size(min = 3,message = "user id has to be at least 3 char")
    private String userId;
    @NotEmpty(message = "product id can't be empty")
    @Size(min = 3,message = "product id has to be at least 3 char")
    private String productId;
    @NotEmpty(message = "balance can't be empty")
    @Positive(message = "balance has to be more than 0")
    private Integer price;
}
