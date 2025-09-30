package com.example.Mini_store_app.admin.category.repo;

import com.example.Mini_store_app.admin.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
