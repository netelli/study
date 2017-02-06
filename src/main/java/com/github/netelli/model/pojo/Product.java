package com.github.netelli.model.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
public class Product {
    @Column(name = "title", length = 100)
    private String title;

//    @Column(name = "categoryId")
//    private int categoryId;

    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id", nullable = false)
    private Brand brand;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    public int getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(int categoryId) {
//        this.categoryId = categoryId;
//    }


    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "title='" + title + '\'' +
//                ", categoryId=" + categoryId +
                ", brand=" + brand.toString() +
                ", id=" + id +
                '}';
    }
}
