package com.example.Mini_store_app.admin.products.controller;


import com.example.Mini_store_app.admin.category.dto.CategoryDTO;
import com.example.Mini_store_app.admin.products.dto.ProductDTO;
import com.example.Mini_store_app.admin.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> getAllProduct(){
        List<ProductDTO> list = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = productService.createProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO1);
    }

    @GetMapping("/byId")
    public ResponseEntity<ProductDTO> getProductById(@RequestParam("productId") Long productId){
        ProductDTO productDTO = productService.getProductById(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PutMapping("")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO){
        ProductDTO productDTO1 = productService.updateProductDTO(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ProductDTO> deleteProduct(@RequestParam("productId") Long productId){
        ProductDTO productDTO = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }
}
