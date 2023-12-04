package com.itgroup.mapper;


import com.itgroup.dto.BrandResponse;
import com.itgroup.dto.BrandRequest;
import com.itgroup.models.Brand;

public class BrandMapper {

    private BrandMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Brand mapToBrand(BrandRequest requestDto) {
        return Brand.builder()
                .name(requestDto.getName())
                .description(requestDto.getDescription())
                .logoUrl(requestDto.getLogoUrl())
                .build();
    }

    public static BrandResponse mapToBrandDto(Brand brand) {
        return BrandResponse.builder()
                .id(brand.getId())
                .name(brand.getName())
                .description(brand.getDescription())
                .logoUrl(brand.getLogoUrl())
                .build();
    }
}
