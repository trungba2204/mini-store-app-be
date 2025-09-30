package com.example.Mini_store_app.admin.products.mapper;


import com.example.Mini_store_app.admin.products.dto.ProductDTO;
import com.example.Mini_store_app.admin.products.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "categoryId", source = "category.id")
    ProductDTO toDTO(Product product);

    @Mapping(target = "category", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
