package com.github.netelli.model;

/**
 * Created by nataliiaku on 1/5/2017.
 */
public class Brands {


    private String title;
    private int id;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Brands{" +
                "title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
