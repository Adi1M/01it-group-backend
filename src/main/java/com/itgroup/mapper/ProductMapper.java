package com.itgroup.mapper;

import com.itgroup.dto.ProductResponse;
import com.itgroup.dto.ProductRequest;
import com.itgroup.models.Brand;
import com.itgroup.models.Category;
import com.itgroup.models.Product;

import java.math.BigDecimal;

public class ProductMapper {

    private ProductMapper() {
        throw new IllegalStateException("Utility class");
    }


    public static Product mapToProduct(ProductRequest requestDto, Category category, Brand brand) {
        return Product.builder()
                .name(requestDto.getName())
                .price(requestDto.getPrice())
                .description(requestDto.getDescription())
                .ratingTotal(new BigDecimal("-1.0"))
                .category(category)
                .brand(brand)
                .imgUrl(requestDto.getImgUrl())
                .quantity(requestDto.getQuantity())
                .build();
    }

    public static ProductResponse mapToProductDto(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .ratingTotal(product.getRatingTotal())
                .category(product.getCategory())
                .brand(product.getBrand())
                .imgUrl(product.getImgUrl())
                .quantity(product.getQuantity())
                .build();
    }

}
