package com.github.netelli.model;

public class Product {
    private String title;
    private int categoryId;
    private int brandId;
    private int id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getBrandId() {
        return brandId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", brandId=" + brandId +
                ", id=" + id +
                '}';
    }
}
