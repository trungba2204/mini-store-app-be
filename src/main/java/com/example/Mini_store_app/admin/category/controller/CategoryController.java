package com.example.Mini_store_app.admin.category.controller;

import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = categoryService.createCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getAllCategory(){
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategory();
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }

    @PutMapping("")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO){
        CategoryDTO categoryDTO1 = categoryService.updateCategoryDTO(categoryDTO);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO1);
    }

    @GetMapping("/getById")
    public ResponseEntity<CategoryDTO> getCategoryById(@RequestParam("categoryId") Long categoryId){
        CategoryDTO categoryDTO1 = categoryService.getCategoryById(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<CategoryDTO> deleteCategory(@RequestParam("categoryId") Long categoryId){
        CategoryDTO categoryDTO = categoryService.deletedCategory(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }
}
