package com.itgroup.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandRequestDto {
    private String name;
    private String description;
    private String logoUrl;
}
