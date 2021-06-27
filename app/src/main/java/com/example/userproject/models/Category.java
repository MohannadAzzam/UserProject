package com.example.userproject.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    private int id;
    @SerializedName("categoryName")
    private String Name;
    @SerializedName("categoryImage")
    private int img;

    public Category(String name, int img) {
        this.id = id;
        Name = name;
        this.img = img;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}

