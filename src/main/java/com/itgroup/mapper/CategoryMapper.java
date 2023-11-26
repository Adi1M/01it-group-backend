package com.itgroup.mapper;

import com.itgroup.dto.CategoryDto;
import com.itgroup.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException("Utility Class");
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        List<CategoryDto> childrenDto = category.getChildren().stream()
                .map(CategoryMapper::mapToCategoryDto).toList();

        return new CategoryDto(
                category.getId(),
                category.getName(),
                childrenDto
        );
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        List<CategoryDto> childrenDto = categoryDto.getChildren();
        if (childrenDto != null && !childrenDto.isEmpty()) {
            List<Category> children = new ArrayList<>();
            for (CategoryDto childDto : childrenDto) {
                Category child = mapToCategory(childDto);
                child.setParent(category);
                children.add(child);
            }
            category.setChildren(children);
        }
        return category;
    }
}
