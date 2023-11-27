package com.itgroup.service;

import com.itgroup.dto.CategoryRequestDto;
import com.itgroup.dto.CategoryDto;
import com.itgroup.mapper.CategoryMapper;
import com.itgroup.models.Category;
import com.itgroup.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, List<CategoryDto>> mapSortedById = new HashMap<>();

        for (Category category : categories) {
            CategoryDto categoryDTO = CategoryMapper.mapToCategoryDto(category);
            Long parentId = (category.getParent() != null) ? category.getParent().getId() : null;
            mapSortedById.computeIfAbsent(parentId, k -> new ArrayList<>()).add(categoryDTO);
        }

        List<CategoryDto> rootCategories = mapSortedById.get(null);
        if (rootCategories != null) {
            for (CategoryDto rootCategory : rootCategories) {
                addChildCategories(rootCategory, mapSortedById);
            }
        }

        return rootCategories;
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = findCategory(id);
        return CategoryMapper.mapToCategoryDto(category);
    }

    public void createCategory(CategoryRequestDto requestDto) {
        if (requestDto.getParent() == null)
            categoryRepository.save(CategoryMapper.mapToCategoryFromCreate(requestDto, null));
        else {
            Category parent = findCategory(requestDto.getParent());
            if (parent != null) {
                Category newCategory = CategoryMapper.mapToCategoryFromCreate(requestDto, parent);
                categoryRepository.save(newCategory);
                parent.getChildren().add(newCategory);
            }
        }
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category existingCategory = findCategory(id);
        List<Category> children = categoryDto.getChildren().stream().map(CategoryMapper::mapToCategory).toList();
        existingCategory.setId(categoryDto.getId());
        existingCategory.setName(categoryDto.getName());
        existingCategory.setChildren(children);

        return CategoryMapper.mapToCategoryDto(existingCategory);
    }

    public void deleteCategory(Long id) {
        Category category = findCategory(id);
        categoryRepository.delete(category);
    }


    private void addChildCategories(CategoryDto category, Map<Long, List<CategoryDto>> mapSortedById) {
        List<CategoryDto> children = mapSortedById.get(category.getId());
        if (children != null) {
            for (CategoryDto child : children) {
                addChildCategories(child, mapSortedById);
            }
            category.setChildren(children);
        }
    }

    private Category findCategory(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }


}
