package com.itgroup.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
    private String logoUrl;
}
