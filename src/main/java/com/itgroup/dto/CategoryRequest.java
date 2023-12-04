package com.itgroup.dto;


import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class CategoryRequest {
    private String name;
    private Long parent;
}
