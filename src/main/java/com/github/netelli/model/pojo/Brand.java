package com.github.netelli.model.pojo;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "brands")
public class Brand {

    @Column(name = "title", length = 100)
    private String title;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private int id;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id", targetEntity = Product.class)
//    private Set<Product> productSet = new HashSet<>();
//
//    public Set<Product> getProductSet() {
//        return productSet;
//    }
//
//    public void setProductSet(Set<Product> productSet) {
//        this.productSet = productSet;
//    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
