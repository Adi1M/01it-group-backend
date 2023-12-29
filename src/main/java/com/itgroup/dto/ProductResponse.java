package com.itgroup.dto;

import com.itgroup.models.Brand;
import com.itgroup.models.Category;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private BigDecimal ratingTotal;
    private Category category;
    private Brand brand;
    private String imgUrl;
    private int quantity;
}