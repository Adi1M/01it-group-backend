package com.itgroup.service;

import com.itgroup.dto.CategoryRequest;
import com.itgroup.dto.CategoryResponse;
import com.itgroup.mapper.CategoryMapper;
import com.itgroup.models.Category;
import com.itgroup.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public List<CategoryResponse> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, List<CategoryResponse>> mapSortedById = new HashMap<>();

        for (Category category : categories) {
            CategoryResponse categoryResponse = CategoryMapper.mapToCategoryDto(category);
            Long parentId = (category.getParent() != null) ? category.getParent().getId() : null;
            mapSortedById.computeIfAbsent(parentId, k -> new ArrayList<>()).add(categoryResponse);
        }

        List<CategoryResponse> rootCategories = mapSortedById.get(null);
        if (rootCategories != null) {
            for (CategoryResponse rootCategory : rootCategories) {
                addChildCategories(rootCategory, mapSortedById);
            }
        }

        return rootCategories;
    }

    public CategoryResponse getCategoryById(Long id) {
        return CategoryMapper.mapToCategoryDto(findCategory(id));
    }

    public void createCategory(CategoryRequest requestDto) {
        if (requestDto.getParent() == null)
            categoryRepository.save(CategoryMapper.mapToCategoryFromCreate(requestDto, null));
        else {
            Category parent = findCategory(requestDto.getParent());
            if (parent != null) {
                Category newCategory = CategoryMapper.mapToCategoryFromCreate(requestDto, parent);
                categoryRepository.save(newCategory);
            }
        }
    }

    public void updateCategory(Long id, CategoryResponse categoryResponse) {
        Category existingCategory = findCategory(categoryResponse.getId());
        List<Category> children = categoryResponse.getChildren().stream()
                .map(CategoryMapper::mapToCategory)
                .collect(ArrayList::new, List::add, List::addAll);
        existingCategory.setId(id);
        existingCategory.setName(categoryResponse.getName());
        existingCategory.setChildren(children);

        categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id) {
        Category category = findCategory(id);
        categoryRepository.delete(category);
    }


    private void addChildCategories(CategoryResponse category, Map<Long, List<CategoryResponse>> mapSortedById) {
        List<CategoryResponse> children = mapSortedById.get(category.getId());
        if (children != null) {
            for (CategoryResponse child : children) {
                addChildCategories(child, mapSortedById);
            }
            category.setChildren(children);
        }
    }

    public Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}
