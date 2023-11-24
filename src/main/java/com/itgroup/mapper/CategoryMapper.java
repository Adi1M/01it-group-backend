package com.itgroup.mapper;

import com.itgroup.dto.CategoryDto;
import com.itgroup.models.Category;

public class CategoryMapper {

    public static CategoryDto mapToCategoryDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getParent()
        );
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        return new Category(
                categoryDto.getId(),
                categoryDto.getName(),
                categoryDto.getParent()
        );
    }
}
