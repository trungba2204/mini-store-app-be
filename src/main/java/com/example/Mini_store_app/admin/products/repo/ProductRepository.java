package com.example.Mini_store_app.admin.products.repo;

import com.example.Mini_store_app.admin.products.entity.Product;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
