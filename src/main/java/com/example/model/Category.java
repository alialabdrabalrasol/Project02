package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class Category {
    @NotEmpty(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotEmpty(message = "name can't be empty")
    @Length( min = 3,message = "name has to be 3 char at least")
    private String name;
}
