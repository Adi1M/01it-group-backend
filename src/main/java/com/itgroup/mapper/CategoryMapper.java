package com.itgroup.mapper;

import com.itgroup.dto.CategoryRequest;
import com.itgroup.dto.CategoryResponse;
import com.itgroup.models.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryMapper {

    private CategoryMapper() {
        throw new IllegalStateException("Utility class");
    }

    public static Category mapToCategory(CategoryResponse categoryResponse) {
        if (categoryResponse.getChildren() == null || categoryResponse.getChildren().isEmpty())
            return Category.builder()
                    .id(categoryResponse.getId())
                    .name(categoryResponse.getName())
                    .imgUrl(categoryResponse.getImgUrl())
                    .children(new ArrayList<>())
                    .build();
        return Category.builder()
                .id(categoryResponse.getId())
                .name(categoryResponse.getName())
                .imgUrl(categoryResponse.getImgUrl())
                .children(categoryResponse.getChildren()
                        .stream().map(CategoryMapper::mapToCategory)
                        .collect(ArrayList::new, List::add, List::addAll))
                .build();
    }

    public static CategoryResponse mapToCategoryDto(Category category) {
        if (category.getChildren() == null)
            CategoryResponse.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .imgUrl(category.getImgUrl())
                    .children(new ArrayList<>())
                    .build();
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .imgUrl(category.getImgUrl())
                .children(category.getChildren().stream()
                        .map(CategoryMapper::mapToCategoryDto)
                        .collect(ArrayList::new, List::add, List::addAll))
                .build();
    }

    public static Category mapToCategoryFromCreate(CategoryRequest requestDto, Category parent) {
        if (parent != null) return Category.builder()
                .name(requestDto.getName())
                .imgUrl(requestDto.getImgUrl())
                .parent(parent)
                .build();
        return Category.builder()
                .name(requestDto.getName())
                .imgUrl(requestDto.getImgUrl())
                .parent(null)
                .build();
    }
}
