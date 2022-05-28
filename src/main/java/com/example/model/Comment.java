package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Comment {
    @NotEmpty(message = "id can't be empty")
    @Size(min = 3,message = "id has to be at least 3 char")
    private String id;
    @NotEmpty(message = "user id can't be empty")
    @Size(min = 3,message = "user id has to be at least 3 char")
    private String userId;
    @NotEmpty(message = "message can't be empty")
    @Size(min = 6,message = "message has to be at least 6 char")
    private String message;
    @NotNull(message = "rate can't be empty")
    @Size(min = 1, max = 5,message = "rating has to be between 1 and 5")
    private Integer rate;
}
