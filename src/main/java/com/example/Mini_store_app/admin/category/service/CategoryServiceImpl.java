package com.example.Mini_store_app.admin.category.service;

import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.category.entity.Category;
import com.example.Mini_store_app.admin.category.mapper.CategoryMapper;
import com.example.Mini_store_app.admin.category.repo.CategoryRepository;
import com.example.Mini_store_app.user.User;
import com.example.Mini_store_app.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    private final UserRepository userRepository;

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        // B1: DTO -> Entity
        Category entity = categoryMapper.toEntity(category);

        // B2: Lưu DB
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);

        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            category.setCreatedBy(String.valueOf(currentUser));
        }
        Instant currentDate = Instant.now();
        entity.setCreatedTime(currentDate);
        Category savedEntity = categoryRepository.save(entity);

        // B3: Entity -> DTO để trả về
        return categoryMapper.toDTO(savedEntity);
    }

    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categoryDTOS = categoryRepository.findAll();
        return categoryDTOS.stream().map(categoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getId());
        if (!optionalCategory.isPresent()){
            throw new Error("Not found id");
        }else {


            Instant currentDate = Instant.now();
            Category category = optionalCategory.get();
            category.setModifiedTime(currentDate);

            // Lấy user hiện tại từ SecurityContext
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = authentication.getName();
            Optional<User> optionalUser = userRepository.findFirstByEmail(email);
            if (optionalUser.isPresent()) {
                User currentUser = optionalUser.get();
                category.setModifiedBy(String.valueOf(currentUser));// cần có field updatedBy trong Product
            }
            Category category1 = categoryRepository.save(category);
            return categoryMapper.toDTO(category1);
        }
    }

    @Override
    public CategoryDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        return categoryMapper.toDTO(category);
    }

    @Override
    public CategoryDTO deletedCategory(Long categoryId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        Category category = optionalCategory.get();
        // Lấy user hiện tại từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            category.setModifiedBy(String.valueOf(currentUser));// cần có field updatedBy trong Product
        }
        category.setDeleted(true);

        Category category1 = categoryRepository.save(category);
        return categoryMapper.toDTO(category1);
    }
}
