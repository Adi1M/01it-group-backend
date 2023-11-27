package com.itgroup.mapper;

import com.itgroup.dto.CategoryRequestDto;
import com.itgroup.dto.CategoryDto;
import com.itgroup.models.Category;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Category mapToCategory(CategoryDto categoryDto) {
        if (categoryDto.getChildren() == null || categoryDto.getChildren().isEmpty())
            return Category.builder()
                    .id(categoryDto.getId())
                    .name(categoryDto.getName())
                    .children(new ArrayList<>())
                    .build();
        return Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .children(categoryDto.getChildren().stream().map(CategoryMapper::mapToCategory).toList())
                .build();
    }

    public static CategoryDto mapToCategoryDto(Category category) {
        if (category.getChildren() == null) return new CategoryDto(
                category.getId(),
                category.getName(),
                new ArrayList<>()
        );
        List<CategoryDto> childrenDto = category.getChildren().stream()
                .map(CategoryMapper::mapToCategoryDto).toList();
        return new CategoryDto(
                category.getId(),
                category.getName(),
                childrenDto
        );
    }

    @SneakyThrows
    public static Category mapToCategoryFromCreate(CategoryRequestDto requestDto, Category parent) {
        if (parent != null) return Category.builder()
                .name(requestDto.getName())
                .parent(parent)
                .build();
        return Category.builder()
                .name(requestDto.getName())
                .parent(null)
                .build();
    }
}
