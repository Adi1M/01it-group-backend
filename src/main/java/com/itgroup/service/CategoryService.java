package com.itgroup.service;

import com.itgroup.dto.CategoryDto;
import com.itgroup.mapper.CategoryMapper;
import com.itgroup.models.Category;
import com.itgroup.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(CategoryMapper::mapToCategoryDto)
                .collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = optional.get();
        return CategoryMapper.mapToCategoryDto(category);
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.mapToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.mapToCategoryDto(savedCategory);
    }

    public CategoryDto updateCategory(CategoryDto category) {
        Category existingCategory = categoryRepository.findById(category.getId()).get();
        existingCategory.setName(category.getName());
        existingCategory.setParent(category.getParent());
        Category updatedCategory = categoryRepository.save(existingCategory);

        return CategoryMapper.mapToCategoryDto(updatedCategory);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }


}
