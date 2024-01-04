package com.itgroup.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FavoritesRequest {
    private Long product;
}
