package com.github.netelli.model;

/**
 * Created by user on 15.12.2016.
 */
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
