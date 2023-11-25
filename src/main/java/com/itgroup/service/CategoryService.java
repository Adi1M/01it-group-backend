package com.itgroup.service;

import com.itgroup.dto.CategoryDto;
import com.itgroup.mapper.CategoryMapper;
import com.itgroup.models.Category;
import com.itgroup.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (Category category : categories) {
            if (category.getParent() == null) {
                CategoryDto categoryDto = CategoryMapper.mapToCategoryDto(category);
                categoryDto.setChildren(buildChildren(categoryDto, categories));
                categoryDtos.add(categoryDto);
            }
        }

        return categoryDtos;
    }

    private List<CategoryDto> buildChildren(CategoryDto parent, List<Category> categories) {
        List<CategoryDto> children = new ArrayList<>();
        for (Category category: categories){
            if(category.getParent() != null && category.getParent().getId().equals(parent.getId())){
                CategoryDto categoryDto = CategoryMapper.mapToCategoryDto(category);
                categoryDto.setChildren(buildChildren(categoryDto, categories));
                children.add(categoryDto);
            }
        }

        return children;
    }

}
