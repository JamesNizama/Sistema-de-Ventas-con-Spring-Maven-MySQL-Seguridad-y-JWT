package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {

    @JsonBackReference
    private SaleDTO sale;

    @NotNull
    @JsonIncludeProperties(value = "idProduct")
    private ProductDTO product;

    @NotNull
    @Min(value = 1)
    private short quantity;

    @NotNull
    @Min(value = 1)
    private double price;

    @NotNull
    @Min(value = 0)
    private double discount;

}
