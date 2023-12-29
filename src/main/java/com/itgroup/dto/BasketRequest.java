package com.itgroup.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BasketRequest {
    private Long product;
    private int quantity;
}
