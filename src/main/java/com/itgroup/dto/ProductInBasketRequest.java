package com.itgroup.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductInBasketRequest {
    private int quantity;
}
