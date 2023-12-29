package com.itgroup.dto;

import com.itgroup.models.Product;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductsInBasketResponse {
    private Product product;
    private int quantity;
    private BigDecimal totalPrice;
}
