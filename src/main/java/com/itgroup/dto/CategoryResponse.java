package com.itgroup.dto;


import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;
    private String name;
    private String imgUrl;
    private List<CategoryResponse> children;
}
