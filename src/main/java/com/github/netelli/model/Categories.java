package com.github.netelli.model;

/**
 * Created by nataliiaku on 1/5/2017.
 */
public class Categories {
    private int id;
    private String title;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
