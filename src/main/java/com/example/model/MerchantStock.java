package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @Data
public class MerchantStock {
    @NotEmpty(message = "id can't be empty")
    @Size(min = 3,message = "id has to be 3 char at least")
    private String ID;
    @NotEmpty(message = "product id can't be empty")
    @Length( min = 3,message = "product id has to be 3 char at least")
    private String productId;
    @NotEmpty(message = "merchant id can't be empty")
    @Length( min = 3,message = "merchant id has to be 3 char at least")
    private String merchantId;
    @NotNull(message = "stock can't be empty")
    @Min( value = 11,message = "stock has to be more than 10")
    private Integer stock;
}
