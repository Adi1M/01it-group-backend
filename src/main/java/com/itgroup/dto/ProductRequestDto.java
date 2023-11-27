package com.itgroup.dto;

import com.itgroup.models.Brand;
import com.itgroup.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDto {
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private Brand brand;
    private String imgUrl;
    private int quantity;
}
