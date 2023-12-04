package com.itgroup.dto;

import com.itgroup.models.Brand;
import com.itgroup.models.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private Category category;
    private Brand brand;
    private String imgUrl;
    private int quantity;
}
