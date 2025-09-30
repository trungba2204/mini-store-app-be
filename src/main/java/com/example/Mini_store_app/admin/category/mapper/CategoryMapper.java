package com.example.Mini_store_app.admin.category.mapper;


import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.category.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO dto);

}
