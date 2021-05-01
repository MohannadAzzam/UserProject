package com.example.userproject.models;

public class Order {
    private int id;
    private int image;
    private int prise;
    private String Order_status;
    private String Date;

    public Order(int image, int prise, String order_status, String date) {
        this.image = image;
        this.prise = prise;
        Order_status = order_status;
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

    public String getOrder_status() {
        return Order_status;
    }

    public void setOrder_status(String order_status) {
        Order_status = order_status;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}

