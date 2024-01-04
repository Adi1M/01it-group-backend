package com.itgroup.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FavoritesResponse {
    List<ProductResponse> favorites;
}
