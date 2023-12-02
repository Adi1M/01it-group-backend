package com.itgroup.mapper;


import com.itgroup.dto.BrandDto;
import com.itgroup.dto.BrandRequestDto;
import com.itgroup.models.Brand;

public class BrandMapper {

    private BrandMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Brand mapToBrand(BrandRequestDto requestDto) {
        return Brand.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .logoUrl(requestDto.getLogoUrl())
                .build();
    }

    public static BrandDto mapToBrandDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .build();
    }
}
