package com.itgroup.service;

import com.itgroup.dto.CategoryDto;
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

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        Map<Long, List<CategoryDto>> categoryMap = new HashMap<>();

        // Construct map of categories
        for (Category category : categories) {
            CategoryDto categoryDTO = CategoryMapper.mapToCategoryDto(category);

            Long parentId = (category.getParent() != null) ? category.getParent().getId() : null;

            categoryMap.computeIfAbsent(parentId, k -> new ArrayList<>()).add(categoryDTO);
        }

        // Build tree structure
        List<CategoryDto> rootCategories = categoryMap.get(null); // Get root categories (categories without parents)
        if (rootCategories != null) {
            for (CategoryDto rootCategory : rootCategories) {
                addChildCategories(rootCategory, categoryMap);
            }
        }

        return rootCategories;
    }

    private void addChildCategories(CategoryDto category, Map<Long, List<CategoryDto>> categoryMap) {
        List<CategoryDto> children = categoryMap.get(category.getId());
        if (children != null) {
            for (CategoryDto child : children) {
                addChildCategories(child, categoryMap);
            }
            category.setChildren(children);
        }
    }
}
