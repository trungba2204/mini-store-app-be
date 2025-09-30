package com.example.Mini_store_app.admin.category.service;

import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.category.entity.Category;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO category);
    List<CategoryDTO> getAllCategory();

    CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO);

    CategoryDTO getCategoryById(Long categoryId);

    CategoryDTO deletedCategory(Long categoryId);

}
