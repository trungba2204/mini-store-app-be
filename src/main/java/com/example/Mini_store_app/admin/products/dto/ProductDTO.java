package com.example.Mini_store_app.admin.products.dto;

import java.time.Instant;

public class ProductDTO {
    private Long id;
    private String productName;
    private String productCode;
    private String productDescription;
    private Long price;
    private Integer quantity;
    private String status;
    private String imageUrl;
    // chỉ lưu id category, không cần full entity
    private Long categoryId;
    private String createdBy;
    private Instant createdTime;
    private String modifiedBy;
    private Instant modifiedTime;
    private Boolean deleted = false;

    // Getter & Setter
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

    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
