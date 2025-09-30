package com.example.Mini_store_app.admin.products.service;

import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.products.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO);

    List<ProductDTO> getAllProducts();

    ProductDTO updateProductDTO(ProductDTO productDTO);

    ProductDTO getProductById(Long productId);

    ProductDTO deleteProduct(Long productId);
}
