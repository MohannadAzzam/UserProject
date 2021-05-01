package com.example.userproject.models;

public class Farm {
    private int id;
    private int image;
    private int prise;
    private String name;
    private String Date;

    public Farm(int image, int prise, String order_status, String date) {
        this.image = image;
        this.prise = prise;
        name = order_status;
        Date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getPrise() {
        return prise;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
