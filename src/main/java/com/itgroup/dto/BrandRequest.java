package com.itgroup.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BrandRequest {
    private String name;
    private String description;
    private String logoUrl;
}
