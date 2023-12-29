package com.itgroup.dto;

import com.itgroup.models.User;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BasketResponse {
    private Long id;
    private List<ProductsInBasketResponse> products;
    private User user;
    private BigDecimal totalPrice;
}
