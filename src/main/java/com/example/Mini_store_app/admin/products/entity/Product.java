package com.example.Mini_store_app.admin.products.entity;


import com.example.Mini_store_app.admin.category.entity.Category;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // Khóa chính

    @Column(name = "product_name", nullable = false)
    private String productName;   // Tên sản phẩm

    @Column(name = "product_code", unique = true, nullable = false)
    private String productCode;   // Mã sản phẩm (duy nhất)

    @Column(name = "product_description")
    private String productDescription;   // Mô tả sản phẩm

    @Column(name = "price", nullable = false)
    private Long price;   // Giá sản phẩm

    @Column(name = "quantity", nullable = false)
    private Integer quantity;   // Số lượng tồn kho

    @Column(name = "status")
    private String status;   // Trạng thái (active/inactive)

    @Column(name = "image_url")
    private String imageUrl;   // Link ảnh sản phẩm (URL hoặc base64)

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;   // Quan hệ N-1: mỗi Product thuộc 1 Category

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_time")
    private Instant createdTime;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    @Column(name = "deleted")
    private Boolean deleted = false;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Instant createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Instant getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Instant modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public boolean setDeleted(Boolean deleted) {
        this.deleted = deleted;
        return false;
    }
}
