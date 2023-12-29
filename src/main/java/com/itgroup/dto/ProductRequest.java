package com.itgroup.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private Long category;
    private Long brand;
    private String imgUrl;
    private int quantity;
}
