package com.example.userproject.models;

import com.google.gson.annotations.SerializedName;

public class Category {
    private int id;
    @SerializedName("name")
    private String Name;
    @SerializedName("url")
    private String img;

    public Category(int id, String name, String img) {
        this.id = id;
        this.Name = name;
        this.img = img;
    }

    public Category(String name, String img) {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
