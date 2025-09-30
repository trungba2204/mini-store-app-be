package com.example.Mini_store_app.admin.products.service;


import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.category.entity.Category;
import com.example.Mini_store_app.admin.category.repo.CategoryRepository;
import com.example.Mini_store_app.admin.products.dto.ProductDTO;
import com.example.Mini_store_app.admin.products.entity.Product;
import com.example.Mini_store_app.admin.products.mapper.ProductMapper;
import com.example.Mini_store_app.admin.products.repo.ProductRepository;
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
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = productMapper.toEntity(productDTO);
        Instant currentDate = Instant.now();
        product.setCreatedTime(currentDate);
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        product.setCategory(category);

        // 🔹 Lấy user đang đăng nhập từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);

        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            product.setCreatedBy(String.valueOf(currentUser));
        }

        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDTO(savedProduct);
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> list= productRepository.findAll();
        return list.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProductDTO(ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(productDTO.getId());
        if (!optionalProduct.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }
        Category category = categoryRepository.findById(productDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));


        Instant currentDate = Instant.now();
        Product product = optionalProduct.get();
        // cập nhật các field từ DTO
        product.setCategory(category);
        product.setModifiedTime(currentDate);

        // Lấy user hiện tại từ SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            product.setModifiedBy(String.valueOf(currentUser));// cần có field updatedBy trong Product
        }

        productRepository.save(product);

        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return productMapper.toDTO(product);
    }

    @Override
    public ProductDTO deleteProduct(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (!optionalProduct.isPresent()){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        }

        Product product = optionalProduct.get();
        product.setDeleted(true);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        Optional<User> optionalUser = userRepository.findFirstByEmail(email);
        if (optionalUser.isPresent()) {
            User currentUser = optionalUser.get();
            product.setModifiedBy(String.valueOf(currentUser));// cần có field updatedBy trong Product
        }
        productRepository.save(product);
        return productMapper.toDTO(product);
    }


}
