package com.itgroup.mapper;

import com.itgroup.dto.ProductDto;
import com.itgroup.dto.ProductRequestDto;
import com.itgroup.models.Product;

import java.math.BigDecimal;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static Product mapToProduct(ProductRequestDto requestDto) {
        return Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .description(requestDto.getDescription())
                .ratingTotal(new BigDecimal("-1.0"))
                .category(requestDto.getCategory())
                .brand(requestDto.getBrand())
                .imgUrl(requestDto.getImgUrl())
                .quantity(requestDto.getQuantity())
                .build();
    }

    public static ProductDto mapToProductDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getRatingTotal(),
                product.getCategory(),
                product.getBrand(),
                product.getImgUrl(),
                product.getQuantity()
        );
    }

}
